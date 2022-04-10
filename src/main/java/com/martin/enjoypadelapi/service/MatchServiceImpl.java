package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Center;
import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.Player;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import com.martin.enjoypadelapi.repository.CenterRepository;
import com.martin.enjoypadelapi.repository.MatchRepository;
import com.martin.enjoypadelapi.repository.PlayerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private CenterRepository centerRepository;

    @Override
    public Flux<Match> findAll() {
        return matchRepository.findAll();
    }

    @Override
    public Mono<Match> findById(long id) throws MatchNotFoundException {
        return matchRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new MatchNotFoundException())));
    }

    @Override
    public void addMatch(MatchDTO matchDTO) throws PlayerNotFoundException, CenterNotFoundException {
        ModelMapper mapper = new ModelMapper();
        Match match = mapper.map(matchDTO, Match.class);

        Mono<Player> player1 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer1())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Player> player2 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer2())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Player> player3 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer3())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Player> player4 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer4())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Center> center = centerRepository.findById(Objects.requireNonNull(matchDTO).getCenter())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CenterNotFoundException())));

        Objects.requireNonNull(player1.block()).getMatches().add(match);
        Objects.requireNonNull(player2.block()).getMatches().add(match);
        Objects.requireNonNull(player3.block()).getMatches().add(match);
        Objects.requireNonNull(player4.block()).getMatches().add(match);
        match.setCenter(center.block());
        matchRepository.save(match);
    }

    @Override
    public void deleteMatch(long id) throws MatchNotFoundException {
        Mono<Match> match = matchRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new MatchNotFoundException())));
        matchRepository.delete(Objects.requireNonNull(match.block()));
    }

    @Override
    public Mono<Match> modifyMatch(long id, MatchDTO matchDTO) throws MatchNotFoundException, PlayerNotFoundException, CenterNotFoundException {

        Mono<Match> matchMono = matchRepository.findById(id)
                .switchIfEmpty(Mono.defer(() -> Mono.error(new MatchNotFoundException())));

        ModelMapper mapper = new ModelMapper();
        Match match = mapper.map(matchDTO, Match.class);

        Mono<Player> player1 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer1())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Player> player2 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer2())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Player> player3 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer3())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Player> player4 = playerRepository.findById(Objects.requireNonNull(matchDTO).getPlayer4())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new PlayerNotFoundException())));

        Mono<Center> center = centerRepository.findById(Objects.requireNonNull(matchDTO).getCenter())
                .switchIfEmpty(Mono.defer(() -> Mono.error(new CenterNotFoundException())));

        Objects.requireNonNull(player1.block()).getMatches().add(match);
        Objects.requireNonNull(player2.block()).getMatches().add(match);
        Objects.requireNonNull(player3.block()).getMatches().add(match);
        Objects.requireNonNull(player4.block()).getMatches().add(match);
        match.setCenter(center.block());
        match.setId(Objects.requireNonNull(matchMono.block()).getId());
        return matchRepository.save(match);
    }
}