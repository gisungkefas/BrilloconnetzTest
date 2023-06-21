package kefas.Brilloconnetz.service.serviceImpl;

import kefas.Brilloconnetz.Entities.User;
import kefas.Brilloconnetz.service.TokenValidationService;
import kefas.Brilloconnetz.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenValidationServiceImpl implements TokenValidationService {

    private final JwtUtil jwtUtil;

    @Override
    public String validateToken(String token, User userDetails) {
        String validationResult = jwtUtil.validateToken(token, userDetails);
        if (validationResult.equals("Verification pass")) {
            return "Verification pass";
        } else {
            return "Verification fails";
        }
    }
}
