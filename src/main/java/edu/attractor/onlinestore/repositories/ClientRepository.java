package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}
