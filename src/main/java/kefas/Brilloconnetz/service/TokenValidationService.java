package kefas.Brilloconnetz.service;

import kefas.Brilloconnetz.Entities.User;

public interface TokenValidationService {
    String validateToken(String token, String username);
}
