package com.martin.enjoypadelapi.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "matches")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String round;
    @Column
    private int duration;
    @Column
    private String date;
    @Column
    private String matchScore;

    @ManyToMany(mappedBy = "matches")
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;

    public Match() {
        players = new ArrayList<>();
    }

    public Match(long id, String round, int duration, String date, String matchScore, List<Player> players, Center center) {
        this.id = id;
        this.round = round;
        this.duration = duration;
        this.date = date;
        this.matchScore = matchScore;
        this.players = players;
        this.center = center;
        this.players =players = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMatchScore() {
        return matchScore;
    }

    public void setMatchScore(String matchScore) {
        this.matchScore = matchScore;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public Center getCenter() {
        return center;
    }

    public void setCenter(Center center) {
        this.center = center;
    }
}

