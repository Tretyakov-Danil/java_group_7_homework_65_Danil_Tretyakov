package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.ClientLoginDto;
import edu.attractor.onlinestore.dtos.ClientRegisterDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.exceptions.ResourceNotFoundException;
import edu.attractor.onlinestore.exceptions.UserAlreadyExistException;
import edu.attractor.onlinestore.services.ClientService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final PasswordEncoder passwordEncoder;


    public ClientController(ClientService clientService, PasswordEncoder passwordEncoder) {
        this.clientService = clientService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public Integer register(@Valid @RequestBody ClientRegisterDto clientDto){
        Optional<Client> clientByEmail = this.clientService.findByEmail(clientDto.getEmail());
        if (clientByEmail.isPresent()) {
            throw new UserAlreadyExistException(String.format("User with email %s already exist", clientDto.getEmail()));
        }
        Client client = Client.builder()
                .email(clientDto.getEmail())
                .username(clientDto.getUsername())
                .password(passwordEncoder.encode(clientDto.getPassword()))
                .build();
        return this.clientService.saveClient(client).getId();
    }

    @PostMapping("/login")
    public Integer login(@Valid @RequestBody ClientLoginDto clientDto) throws ResourceNotFoundException {
        Optional<Client> findToLogin = this.clientService.
                findByEmailAndPassword(clientDto.getEmail(), clientDto.getPassword());
        if (findToLogin.isEmpty()) throw new ResourceNotFoundException();
        return findToLogin.get().getId();
    }

}