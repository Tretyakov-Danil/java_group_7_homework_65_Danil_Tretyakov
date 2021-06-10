package edu.attractor.onlinestore.entities;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder

@Entity
@Table(name = "orders")
@RequiredArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @ManyToOne
    private Product product;

    @Column
    @ManyToOne
    private Client client;

    @Column
    private LocalDateTime dateOfOrder;

    @Column
    private Boolean isPaid;

}
