package edu.attractor.onlinestore.entities;

import edu.attractor.onlinestore.enums.ProductType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Builder

@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private ProductType type;

    @Column(name = "imagePath", length = 200)
    private String imagePath;

    @Column
    private BigDecimal price;

    @ManyToOne
    private Brand brand;

}
