package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Player;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface PlayerRepository extends ReactiveMongoRepository<Player, Long> {
    Flux<Player> findAll();

//    @Query(value = "SELECT * FROM \"players\" WHERE (\"availability\" = true)", nativeQuery = true)
//    Flux<Player> findAll(boolean availability);
}
