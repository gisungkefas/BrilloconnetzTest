package kefas.Brilloconnetz.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@Builder
public class UserDetailsDto {

    @NotBlank(message = "Username cannot be empty!!")
    private String username;

    @NotBlank(message = "Email cannot be empty!!")
    private String email;

    @NotBlank(message = "Password cannot be empty")
    private String password;

    @NotBlank(message = "Date of Birth cannot be empty and must be above 16 years")
    private Date dateOfBirth;
}
