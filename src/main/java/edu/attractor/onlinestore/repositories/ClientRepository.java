package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    Optional<Client> findByEmail(String email);

    Optional<Client> findByUsername(String username);
}
