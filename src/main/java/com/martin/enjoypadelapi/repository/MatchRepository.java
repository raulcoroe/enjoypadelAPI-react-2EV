package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Match;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface MatchRepository extends ReactiveMongoRepository<Match, Long> {
    Flux<Match> findAll();
}
