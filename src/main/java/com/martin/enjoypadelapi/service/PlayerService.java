package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PlayerService {
    Flux<Player> findAll();
    Mono<Player> findById(long id) throws PlayerNotFoundException;
    void addPlayer(Player newPlayer);
    void deletePlayer(long id) throws PlayerNotFoundException;
    Mono<Player> modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException;
}
