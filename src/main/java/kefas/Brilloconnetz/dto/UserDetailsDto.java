package kefas.Brilloconnetz.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
public class UserDetailsDto {

    private String username;

    private String email;

    private String password;

    private Date dateOfBirth;
}
