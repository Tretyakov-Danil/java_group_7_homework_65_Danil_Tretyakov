package edu.attractor.onlinestore.services;

import edu.attractor.onlinestore.entities.Review;
import edu.attractor.onlinestore.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    public List<Review> getReviewsByProductId(String productId) {
        return this.reviewRepository.findAllByProduct_Id(productId);
    }
}
