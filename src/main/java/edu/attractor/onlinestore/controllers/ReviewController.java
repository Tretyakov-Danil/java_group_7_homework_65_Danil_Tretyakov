package edu.attractor.onlinestore.controllers;

import edu.attractor.onlinestore.dtos.ReviewDto;
import edu.attractor.onlinestore.services.ReviewService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper = new ModelMapper();

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/{productId}")
    public String showReviews(Model model, @PathVariable String productId){
        model.addAttribute("reviews", this.reviewService.getReviewsByProductId(productId).stream()
                .map(review -> modelMapper.map(review, ReviewDto.class)).collect(Collectors.toList()));
        return "reviews";
    }
}
