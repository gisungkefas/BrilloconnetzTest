package kefas.Brilloconnetz.dto;

import kefas.Brilloconnetz.Entities.User;
import lombok.Data;

@Data
public class TokenValidationDto {

    private String token;
    private User userDetails;

}
