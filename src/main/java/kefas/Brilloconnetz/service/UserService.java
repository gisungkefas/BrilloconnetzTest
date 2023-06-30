package kefas.Brilloconnetz.service;

import kefas.Brilloconnetz.pojos.request.CreateUserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> signUp(CreateUserRequest createUserRequest);
}