package edu.attractor.onlinestore.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder

@Entity
@Table(name = "reviews")
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String review;

    @Column
    private LocalDateTime dateOfReview;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;
}
