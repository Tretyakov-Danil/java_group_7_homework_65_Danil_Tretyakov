package edu.attractor.onlinestore.services;

import edu.attractor.onlinestore.dtos.ClientRegisterDto;
import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.exceptions.UserNotFoundException;
import edu.attractor.onlinestore.repositories.ClientRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

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

    public Client isClientOnline(Optional<Authentication> auth, Model model){
        if (auth.isEmpty()){
            model.addAttribute("isOnline", false);
            return null;
        }
        Client client = (Client) auth.get().getPrincipal();
        model.addAttribute("username", this.clientRepository.findByEmail(client.getEmail()).get().getUsername());
        model.addAttribute("isOnline", true);
        return client;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return clientRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }

    public String createNewPassword(String email) {
        Client client = this.clientRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
        String newPassword = createRandomPassword();
        client.setPassword(passwordEncoder.encode(newPassword));
        this.clientRepository.save(client);
        return newPassword;
    }

    private String createRandomPassword() {
        return "super_password";
    }
}
