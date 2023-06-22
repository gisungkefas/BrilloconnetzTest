package kefas.Brilloconnetz.service.serviceImpl;

import kefas.Brilloconnetz.Entities.User;
import kefas.Brilloconnetz.service.TokenValidationService;
import kefas.Brilloconnetz.util.JwtUtil;
import kefas.Brilloconnetz.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenValidationServiceImpl implements TokenValidationService {

    private final JwtUtils jwtUtil;

    @Override
    public String validateToken(String token, String username) {
        Boolean validationResult = jwtUtil.isTokenValid(token, username);
        return validationResult ? "Verification pass" : "Verification fails";
    }
}
