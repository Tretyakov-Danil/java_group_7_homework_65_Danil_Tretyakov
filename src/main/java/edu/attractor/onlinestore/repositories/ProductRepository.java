package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Product;
import edu.attractor.onlinestore.enums.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findAllByNameContaining(String name);

    List<Product> findAllByType(ProductType type);

    List<Product> findAllByBrand_Name(String brand_id);

    List<Product> findAllByAmountIsLessThan(int amount);

    @Transactional
    @Modifying
    @Query("update Product p set p.amount = p.amount - ?2 where p.id = ?1")
    void changeAmount(int productId, int amount);

    @Transactional
    @Modifying
    @Query("update Product p set p.amount = p.amount + ?2 where p.id = ?1")
    void addAmount(int productId, int amount);
}
