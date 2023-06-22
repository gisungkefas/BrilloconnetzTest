package kefas.Brilloconnetz.controller;

import kefas.Brilloconnetz.service.TokenValidationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/validate")
public class TokenValidationController {

    private final TokenValidationService tokenValidationService;

    @PostMapping("/verify-token")
    public String validateToken(@RequestParam(name = "token") String token, @RequestParam(name = "username") String username){
        return tokenValidationService.validateToken(token, username);
    }
}
