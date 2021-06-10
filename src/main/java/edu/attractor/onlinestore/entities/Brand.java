package edu.attractor.onlinestore.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder

@Entity
@Table(name = "brands")
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 40)
    private String name;

}
