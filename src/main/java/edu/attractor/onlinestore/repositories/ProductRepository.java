package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
