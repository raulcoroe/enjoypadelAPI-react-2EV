package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface MatchService {
    Flux<Match> findAll();
    Mono<Match> findById(long id) throws MatchNotFoundException;
    void addMatch(MatchDTO matchDTO) throws PlayerNotFoundException, CenterNotFoundException;
    void deleteMatch(long id) throws MatchNotFoundException;
    Mono<Match> modifyMatch(long id, MatchDTO matchDTO) throws MatchNotFoundException, PlayerNotFoundException, CenterNotFoundException;
}
