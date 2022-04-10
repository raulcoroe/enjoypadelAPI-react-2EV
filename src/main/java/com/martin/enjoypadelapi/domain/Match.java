package com.martin.enjoypadelapi.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "matches")
public class Match {

    @Id
    private String id;
    @Field
    private String round;
    @Field
    private int duration;
    @Field
    private String date;
    @Field
    private String matchScore;

    @ManyToMany(mappedBy = "matches", cascade = CascadeType.ALL)
    private List<Player> players;

    @ManyToOne
    @JoinColumn(name = "center_id")
    private Center center;
}

