package kefas.Brilloconnetz.controller;

import kefas.Brilloconnetz.Entities.User;
import kefas.Brilloconnetz.service.TokenValidationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/validate")
public class TokenValidationController {

    private final TokenValidationService tokenValidationService;

    @PostMapping("/verify-token")
    public String validateToken(@RequestBody String token,@RequestBody User userDetails){
        return tokenValidationService.validateToken(token, userDetails);
    }
}
