package kefas.Brilloconnetz.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UserDetailsDto {

    @NotBlank(message = "Username cannot be empty")
    @Min(value = 4, message = "The Length is less than 4")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Not a valid Email")
    private String email;


    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*()_+!])(?=.*[a-zA-Z]).{8,}$", message = "Invalid password format. Date should be in yyyy-MM-dd format.")
    private String password;

//    @NotBlank(message = "Date of birth cannot be empty")
//    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "Invalid date format. Date should be in yyyy-MM-dd format.")
    private String dateOfBirth;
}
