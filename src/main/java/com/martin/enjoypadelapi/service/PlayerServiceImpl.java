package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    @Override
    public Flux<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public Mono<Player> findById(long id) throws PlayerNotFoundException {
        return playerRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));
    }

    @Override
    public void addPlayer(Player newPlayer) {
        playerRepository.save(newPlayer);
    }

    @Override
    public void deletePlayer(long id) throws PlayerNotFoundException {
        Mono<Player> player = playerRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));
        playerRepository.delete(Objects.requireNonNull(player.block()));
    }

    @Override
    public Mono<Player> modifyPlayer(long id, Player newPlayer) throws PlayerNotFoundException {
        Mono<Player> playerMono = playerRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));
        newPlayer.setId(Objects.requireNonNull(playerMono.block()).getId());
        return playerRepository.save(newPlayer);
    }
}
