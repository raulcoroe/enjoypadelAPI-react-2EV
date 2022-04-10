package com.martin.enjoypadelapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "players")
public class Player {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String surname;
    @Field
    private String level;
    @Field
    private boolean availability;
    @Field
    private byte[] image;

    @JoinTable(
            name = "rel_players_matches",
            joinColumns = @JoinColumn(name = "player_id"),
            inverseJoinColumns = @JoinColumn(name = "match_id")
    )
    @ManyToMany
    @JsonBackReference(value = "player_matches")
    private List<Match> matches;
}
