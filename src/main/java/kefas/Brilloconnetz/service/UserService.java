package kefas.Brilloconnetz.service;

import kefas.Brilloconnetz.dto.UserDetailsDto;
import kefas.Brilloconnetz.response.BaseResponse;

public interface UserService {
    BaseResponse signUp(UserDetailsDto entitiesDetailsDto);
}