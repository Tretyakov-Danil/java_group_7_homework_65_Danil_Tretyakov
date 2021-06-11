package edu.attractor.onlinestore.repositories;

import edu.attractor.onlinestore.entities.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findAllByProduct_Id(String productId);
}
