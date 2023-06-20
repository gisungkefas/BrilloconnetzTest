package kefas.Brilloconnetz.service.serviceImpl;

import kefas.Brilloconnetz.dto.UserDetailsDto;
import kefas.Brilloconnetz.response.BaseResponse;
import kefas.Brilloconnetz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {


    @Override
    public BaseResponse signUp(UserDetailsDto entitiesDetailsDto) {
        return null;
    }
}
