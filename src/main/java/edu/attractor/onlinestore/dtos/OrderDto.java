package edu.attractor.onlinestore.dtos;

import edu.attractor.onlinestore.entities.Client;
import edu.attractor.onlinestore.entities.Product;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode

@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private int id;
    private Product product;
    private Client client;
    private LocalDateTime dateOfOrder;
    private Boolean isPaid;
}