package kefas.Brilloconnetz.service;

import kefas.Brilloconnetz.dto.EntitiesDetailsDto;
import kefas.Brilloconnetz.response.BaseResponse;

public interface EntityService {
    BaseResponse signUp(EntitiesDetailsDto entitiesDetailsDto);
}
