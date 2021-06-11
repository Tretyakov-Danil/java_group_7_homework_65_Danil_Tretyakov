package edu.attractor.onlinestore.dtos;

import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.entities.Product;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode

@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {
    private int id;
    private String review;
    private LocalDateTime dateOfReview;
    private Client client;
    private Product product;
}
