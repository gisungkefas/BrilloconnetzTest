package kefas.Brilloconnetz.service;

import kefas.Brilloconnetz.Entities.User;
import kefas.Brilloconnetz.pojos.CreateUserRequest;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<?> signUp(CreateUserRequest createUserRequest);
}