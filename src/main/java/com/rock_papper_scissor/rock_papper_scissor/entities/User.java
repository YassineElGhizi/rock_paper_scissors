package com.rock_papper_scissor.rock_papper_scissor.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String name;
    private String password;
    private String token;
    private String img;


    @JsonBackReference
    @OneToMany(mappedBy = "user")//removing the pivot table
    private List<Game> games;

    public List<Game> get_wins() {
        return this.games;
    }

}
