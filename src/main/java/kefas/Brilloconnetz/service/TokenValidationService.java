package kefas.Brilloconnetz.service;

public interface TokenValidationService {
    String validateToken(String token, String username);
}
