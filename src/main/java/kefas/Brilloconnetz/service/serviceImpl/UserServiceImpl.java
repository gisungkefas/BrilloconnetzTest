package kefas.Brilloconnetz.service.serviceImpl;

import kefas.Brilloconnetz.pojos.request.CreateUserRequest;
import kefas.Brilloconnetz.service.UserService;
import kefas.Brilloconnetz.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final JwtUtils jwtUtil;

    @Override
    public ResponseEntity<?> signUp(CreateUserRequest createUserRequest) {

        return new ResponseEntity(jwtUtil.generateToken(createUserRequest.getUsername()), HttpStatus.CREATED);
    }
}
