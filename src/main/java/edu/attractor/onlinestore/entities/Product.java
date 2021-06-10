package edu.attractor.onlinestore.entities;

import edu.attractor.onlinestore.enums.ProductType;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder

@Entity
@Table(name = "products")
@RequiredArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column
    private String description;

    @Column(length = 60)
    private ProductType type;

    @Column(name = "imagePath", length = 200)
    private String imagePath;

    @Column
    private int amount;

    @Column
    private BigDecimal price;

}
