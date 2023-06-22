package kefas.Brilloconnetz.Entities;

import kefas.Brilloconnetz.pojos.CreateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TokenValidation {

    private String token;
    private String username;
}
