package edu.attractor.onlinestore.dtos;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
@EqualsAndHashCode

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor
public class ClientLoginDto {

    private Integer id;

    @Email
    @NotBlank
    private String email;

    @NotNull
    @Size(min = 8, max = 30, message = "Password length had to be >= 4 and <=30")
    private String password;
}
