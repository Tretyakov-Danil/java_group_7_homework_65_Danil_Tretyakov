package edu.attractor.onlinestore.utils;

import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.repositories.ClientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class InitClients {
    private final PasswordEncoder encoder;

    public InitClients(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner init(ClientRepository clientRepo) {
        return (args) -> {
            clientRepo.deleteAll();
            clientRepo.save(Client.builder()
                    .email("test@test.com")
                    .username("testClient")
                    .password(encoder.encode("password")).build());

            clientRepo.save(Client.builder()
                    .email("test2@testing.com")
                    .username("clientNum.2")
                    .password(encoder.encode("wordpass")).build());

            clientRepo.save(Client.builder()
                    .email("yandex@yan.ru")
                    .username("YandexBot")
                    .password(encoder.encode("alice")).build());
        };

    }
}
