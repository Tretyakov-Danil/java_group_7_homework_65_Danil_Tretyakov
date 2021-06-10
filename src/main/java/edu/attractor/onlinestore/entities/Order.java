package edu.attractor.onlinestore.entities;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder

@Entity
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Client client;

    @Column
    private LocalDateTime dateOfOrder;

    @Column
    private Boolean isPaid;

}
