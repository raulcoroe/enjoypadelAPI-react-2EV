package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Center;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.List;

@Repository
public interface CenterRepository extends ReactiveMongoRepository<Center, Long> {
    Flux<Center> findAll();
}
