package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CenterService {
    Flux<Center> findAll();
    Mono<Center> findById(long id) throws CenterNotFoundException;
    Mono<Center> addCenter(Center center);
    void deleteCenter(long id) throws CenterNotFoundException;
    Mono<Center> modifyCenter(long id, Center newCenter) throws CenterNotFoundException;
}
