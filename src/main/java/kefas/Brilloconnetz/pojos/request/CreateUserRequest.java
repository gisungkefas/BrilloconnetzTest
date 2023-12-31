package kefas.Brilloconnetz.pojos.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import kefas.Brilloconnetz.anotations.MinAge;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest {

    @NotBlank(message = "Username cannot be empty")
    @Size(min = 4, message = "The Length is less than 4")
    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Not a valid Email")
    private String email;

    @Schema(example = "string")
    @NotBlank(message = "Password cannot be empty")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*()_+!])(?=.*[a-zA-Z]).{8,}$",
            message = "Invalid password format. Password should contain at least Uppercase(A-Z), Lowercase(a-z), Special Character(/?.@!#$#% etc) and a Number(0-9)")
    private String password;

    @NotNull(message = "Date of birth cannot be empty")
    @MinAge(value = 16, message = "Age must be at least 16")
    private LocalDate dateOfBirth;
}
