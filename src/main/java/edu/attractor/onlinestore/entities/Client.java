package edu.attractor.onlinestore.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder

@Entity
@Table(name = "clients")
@AllArgsConstructor
@NoArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 50)
    private String username;

    @Column(length = 30)
    private String password;

    @Column(length = 60)
    private String email;

}
