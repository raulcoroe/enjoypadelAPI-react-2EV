package com.martin.enjoypadelapi.service;

import com.martin.enjoypadelapi.domain.Match;
import com.martin.enjoypadelapi.domain.dto.MatchDTO;
import com.martin.enjoypadelapi.exception.CenterNotFoundException;
import com.martin.enjoypadelapi.exception.MatchNotFoundException;
import com.martin.enjoypadelapi.exception.PlayerNotFoundException;

import java.util.List;

public interface MatchService {
    List<Match> findAll();
    Match findById(long id) throws MatchNotFoundException;
    void addMatch(MatchDTO matchDTO) throws PlayerNotFoundException, CenterNotFoundException;
    Match deleteMatch(long id) throws MatchNotFoundException;
    Match modifyMatch(long id, Match match) throws MatchNotFoundException;
}
