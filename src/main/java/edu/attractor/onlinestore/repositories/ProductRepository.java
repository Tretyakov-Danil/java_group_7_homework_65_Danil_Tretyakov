package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByTypeContaining(String type);
}
