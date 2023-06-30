package kefas.Brilloconnetz.controller;

import jakarta.validation.Valid;
import kefas.Brilloconnetz.pojos.request.CreateUserRequest;
import kefas.Brilloconnetz.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return userService.signUp(createUserRequest);
    }
}
