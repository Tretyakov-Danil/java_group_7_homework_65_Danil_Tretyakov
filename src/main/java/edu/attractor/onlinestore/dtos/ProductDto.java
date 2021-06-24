package edu.attractor.onlinestore.dtos;

import edu.attractor.onlinestore.entities.Brand;
import edu.attractor.onlinestore.enums.ProductType;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@EqualsAndHashCode

@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private int id;
    private String name;
    private String description;
    private ProductType type;
    private String imagePath;
    private int amount;
    private BigDecimal price;
    private Brand brand;
}
