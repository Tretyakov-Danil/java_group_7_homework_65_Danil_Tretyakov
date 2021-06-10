package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
