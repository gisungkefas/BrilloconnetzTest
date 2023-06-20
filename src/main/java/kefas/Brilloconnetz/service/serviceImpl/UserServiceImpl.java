package kefas.Brilloconnetz.service.serviceImpl;

import kefas.Brilloconnetz.Entities.User;
import kefas.Brilloconnetz.Enum.ResponseCodeEnum;
import kefas.Brilloconnetz.dto.UserDetailsDto;
import kefas.Brilloconnetz.pojos.CreateUserRequest;
import kefas.Brilloconnetz.repository.UserRepository;
import kefas.Brilloconnetz.response.BaseResponse;
import kefas.Brilloconnetz.security.JwtUtil;
import kefas.Brilloconnetz.service.UserService;
import kefas.Brilloconnetz.util.AppUtil;
import kefas.Brilloconnetz.util.ResponseCodeUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

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

        if (createUserRequest.getUsername().isEmpty()) {
            return updateResponseData(response, ResponseCodeEnum.ERROR, "Username name cannot be empty.");
        }

        if (!appUtil.validEmail(createUserRequest.getEmail())) {
            return updateResponseData(response, ResponseCodeEnum.ERROR_EMAIL_INVALID);
        }

        if (createUserRequest.getPassword().isEmpty()) {
            return updateResponseData(response, ResponseCodeEnum.ERROR, "Password cannot be empty.");
        }

        LocalDate dateOfBirth = createUserRequest.getDateOfBirth();
        if (dateOfBirth == null || Period.between(dateOfBirth, currentDate).getYears() < 16) {
            return updateResponseData(response, ResponseCodeEnum.ERROR,
                    "Date of birth must not be empty and user must be at least 16 years old.");
        }

        boolean isUserExists = userRepository.existsByEmail(createUserRequest.getEmail());
        if (isUserExists) {
            return updateResponseData(response, ResponseCodeEnum.ERROR_DUPLICATE_USER);
        }

        User newUser = new User();
        newUser.setUsername(createUserRequest.getUsername());
        newUser.setEmail(createUserRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        newUser.setDateOfBirth(dateOfBirth);

        String token = jwtUtil.generateSignUpConfirmationToken(createUserRequest.getEmail());
        newUser.setIsActive(false);
        newUser.setConfirmationToken(token);

        return updateResponseData(response, ResponseCodeEnum.SUCCESS, "Token has been received");
    }

    private BaseResponse updateResponseData(BaseResponse response, ResponseCodeEnum code, String message) {
        response.setCode(code.getCode());
        response.setMessage(message);
        return response;
    }
}
