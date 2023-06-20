package kefas.Brilloconnetz.service.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import kefas.Brilloconnetz.Entities.User;
import kefas.Brilloconnetz.Enum.ResponseCodeEnum;
import kefas.Brilloconnetz.pojos.CreateUserRequest;
import kefas.Brilloconnetz.repository.UserRepository;
import kefas.Brilloconnetz.response.BaseResponse;
import kefas.Brilloconnetz.security.JwtUtil;
import kefas.Brilloconnetz.service.UserService;
import kefas.Brilloconnetz.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AppUtil appUtil;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public BaseResponse signUp(CreateUserRequest createUserRequest) {
        BaseResponse response = new BaseResponse();

        LocalDate currentDate = LocalDate.now();

        List<String> errorFields = new ArrayList<>();

        String username = createUserRequest.getUsername();
        if (username.isEmpty() || username.length() < 4) {
            errorFields.add("Username: not empty, minimum 4 characters");
        }

        String email = createUserRequest.getEmail();
        if (email.isEmpty() || !appUtil.validEmail(email)) {
            errorFields.add("Email: not empty, valid email address");
        }

        String password = createUserRequest.getPassword();
        if (password.isEmpty() || !isStrongPassword(password)) {
            errorFields.add("Password: not empty, strong password with at least 1 uppercase, 1 special character, 1 number, and minimum 8 characters");
        }

        LocalDate dateOfBirth = createUserRequest.getDateOfBirth();
        if (dateOfBirth == null || dateOfBirth.isAfter(currentDate.minusYears(16))) {
            errorFields.add("Date of Birth: not empty, 16 years or greater");
        }

        if (!errorFields.isEmpty()) {
            String errorMessage = "Validation failed for the following fields:";
            for (String errorField : errorFields) {
                errorMessage += "\n" + errorField;
            }
            return updateResponseData(response, ResponseCodeEnum.ERROR, errorMessage);
        }

        boolean userExists = userRepository.existsByEmail(createUserRequest.getEmail());
        if (userExists) {
            return updateResponseData(response, ResponseCodeEnum.ERROR_DUPLICATE_USER, "User with the current Email already exists");
        }

        User newUser = new User();
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        newUser.setDateOfBirth(dateOfBirth);

        String token = jwtUtil.generateSignUpConfirmationToken(createUserRequest.getEmail());
        newUser.setIsActive(false);
        newUser.setConfirmationToken(token);

        String jwt = generateJwt(newUser);
        return updateResponseData(response, ResponseCodeEnum.SUCCESS, jwt);
    }

    private BaseResponse updateResponseData(BaseResponse response, ResponseCodeEnum code, String message) {
        response.setCode(code.getCode());
        response.setDescription(message);
        return response;
    }

    private boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*()_+!])(?=.*[a-zA-Z]).{8,}$";
        return password.matches(passwordRegex);
    }

    private String generateJwt(User user) {
        String username = user.getUsername();
        String email = user.getEmail();

        Claims claims = Jwts.claims().setSubject(username);
        claims.put("email", email);

        Instant expirationTime = Instant.now().plus(1, ChronoUnit.DAYS);

        String jwt = Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(expirationTime))
                .signWith(SignatureAlgorithm.HS256, "yourSecretKey")  // Replace "yourSecretKey" with your actual secret key
                .compact();

        return jwt;
    }

}
