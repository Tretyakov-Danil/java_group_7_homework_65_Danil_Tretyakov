package edu.attractor.onlinestore.dtos;

import lombok.*;

@Data
@Builder
@EqualsAndHashCode

@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
    private int id;
    private String username;
    private String password;
    private String email;
}
