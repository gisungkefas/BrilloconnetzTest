package kefas.Brilloconnetz.util;

import io.jsonwebtoken.*;
import kefas.Brilloconnetz.Entities.User;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Service
public class JwtUtil {

    private String SECRET_KEY = "secret";

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }


    public String generateToken(User userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        claims.put("email", userDetails.getEmail());
        claims.put("password", userDetails.getPassword());
        claims.put("dateOfBirth", userDetails.getDateOfBirth());
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TimeUnit.HOURS.toMillis(24)))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public String validateToken(String token, User userDetails) {
        try {
            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();

            if (!claims.get("username").equals(userDetails.getUsername())) {
                return "Verification fails";
            }
            if (!claims.get("email").equals(userDetails.getEmail())) {
                return "Verification fails";
            }

            if (!claims.get("password").equals(userDetails.getPassword())) {
                return "Verification fails";
            }

            if (!claims.get("dateOfBirth").equals(userDetails.getDateOfBirth())) {
                return "Verification fails";
            }

            return "Verification pass";
        } catch (JwtException e) {
            return "Verification fails";
        }
    }
}
