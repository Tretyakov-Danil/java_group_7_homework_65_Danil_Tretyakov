package edu.attractor.onlinestore.services;

import edu.attractor.onlinestore.dtos.ClientLoginDto;
import edu.attractor.onlinestore.dtos.ClientRegisterDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.exceptions.ResourceNotFoundException;
import edu.attractor.onlinestore.repositories.ClientRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;

    public ClientService(ClientRepository clientRepository, PasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Client> findByEmail(String email) {
        return this.clientRepository.findByEmail(email);
    }

    public Client saveClient(ClientRegisterDto clientDto) {
        Client client = Client.builder()
                .email(clientDto.getEmail())
                .username(clientDto.getUsername())
                .password(this.passwordEncoder.encode(clientDto.getPassword()))
                .build();
        return this.clientRepository.save(client);
    }

    public Optional<Client> findByEmailAndPassword(ClientLoginDto clientLoginDto){
        int i = 1;
        return this.clientRepository.findByEmailAndPassword(clientLoginDto.getEmail(),
                this.passwordEncoder.encode(clientLoginDto.getPassword()));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return  clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", email)));
    }
}
