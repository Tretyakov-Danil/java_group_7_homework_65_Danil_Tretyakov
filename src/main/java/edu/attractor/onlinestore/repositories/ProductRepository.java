package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.enums.ProductType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllByNameContaining(String name, Pageable pageable);

    Page<Product> findAllByType(ProductType type, Pageable pageable);

    Page<Product> findAllByBrand_Id(int brand_id, Pageable pageable);
}
