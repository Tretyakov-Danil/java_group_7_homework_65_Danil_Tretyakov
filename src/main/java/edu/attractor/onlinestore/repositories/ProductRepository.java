package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByType(ProductType type);

    List<Product> findAllByBrand_Name(String brand_id);

    List<Product> findAllByAmountIsLessThan(int amount);
}
