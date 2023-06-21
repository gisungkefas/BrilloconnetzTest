package kefas.Brilloconnetz.service.serviceImpl;

import kefas.Brilloconnetz.Entities.User;
import kefas.Brilloconnetz.pojos.CreateUserRequest;
import kefas.Brilloconnetz.util.JwtUtil;
import kefas.Brilloconnetz.service.UserService;
import kefas.Brilloconnetz.util.AppUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.*;
import java.time.LocalDate;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final AppUtil appUtil;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<?> signUp(CreateUserRequest createUserRequest) {
        LocalDate currentDate = LocalDate.now();

        Map<String, String> errorFields = new ConcurrentHashMap<>();

        CompletableFuture<Void> usernameValidation = CompletableFuture.runAsync(() -> {
            String username = createUserRequest.getUsername();
            if (username.isEmpty() || username.length() < 4) {
                errorFields.put("Username", "not empty, minimum 4 characters");
            }
        });

        CompletableFuture<Void> emailValidation = CompletableFuture.runAsync(() -> {
            String email = createUserRequest.getEmail();
            if (email.isEmpty() || !appUtil.validEmail(email)) {
                errorFields.put("Email", "not empty, valid email address");
            }
        });

        CompletableFuture<Void> passwordValidation = CompletableFuture.runAsync(() -> {
            String password = createUserRequest.getPassword();
            if (password.isEmpty() || !isStrongPassword(password)) {
                errorFields.put("Password", "not empty, strong password with at least 1 uppercase, 1 special character, 1 number, and minimum 8 characters");
            }
        });

        CompletableFuture<Void> dateOfBirthValidation = CompletableFuture.runAsync(() -> {
            LocalDate dateOfBirth = createUserRequest.getDateOfBirth();
            if (dateOfBirth == null || dateOfBirth.isAfter(currentDate.minusYears(16))) {
                errorFields.put("Date of Birth", "not empty, 16 years or greater");
            }
        });

        CompletableFuture.allOf(
                usernameValidation,
                emailValidation,
                passwordValidation,
                dateOfBirthValidation
        ).join();

        if (!errorFields.isEmpty()) {
            return new ResponseEntity<>(errorFields, HttpStatus.BAD_REQUEST);
        }

        User newUser = new User();
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPassword(createUserRequest.getPassword());
        newUser.setDateOfBirth(createUserRequest.getDateOfBirth());

        String jwtToken = jwtUtil.generateToken(newUser);
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    private boolean isStrongPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&*()_+!])(?=.*[a-zA-Z]).{8,}$";
        return password.matches(passwordRegex);
    }



}
