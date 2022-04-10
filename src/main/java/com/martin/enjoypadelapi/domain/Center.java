package com.martin.enjoypadelapi.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "centers")
public class Center {
    @Id
    private String id;
    @Field
    private String name;
    @Field
    private String longitude;
    @Field
    private String latitude;

    @JsonBackReference
    @OneToMany(mappedBy = "center")
    private List<Match> matches;
}
