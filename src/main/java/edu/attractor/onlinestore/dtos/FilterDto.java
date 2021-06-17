package edu.attractor.onlinestore.dtos;

import edu.attractor.onlinestore.enums.ProductType;
import lombok.Data;


@Data
public class FilterDto {
    private String name;
    private String type;
    private String brand;
    private int maxAmount;
}
