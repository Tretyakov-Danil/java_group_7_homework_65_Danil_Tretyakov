package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByUsername(String username);

    Optional<Client> findByEmailAndPassword(String email, String password);
}
