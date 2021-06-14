package edu.attractor.onlinestore.dtos;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@EqualsAndHashCode

@AllArgsConstructor
@NoArgsConstructor
public class ClientRegisterDto {
    private int id;

    @NotBlank
    private String username;

    @NotNull
    @Size(min = 8, max = 30, message = "Password length had to be >= 4 and <=30")
    private String password;

    @Email
    @NotBlank
    private String email;
}
