package kefas.Brilloconnetz.service;

import kefas.Brilloconnetz.pojos.CreateUserRequest;
import kefas.Brilloconnetz.response.BaseResponse;

public interface UserService {
    BaseResponse signUp(CreateUserRequest createUserRequest);
}