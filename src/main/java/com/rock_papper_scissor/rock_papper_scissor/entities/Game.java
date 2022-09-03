package com.rock_papper_scissor.rock_papper_scissor.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    public Game(Integer state, User user) {
        this.state = state;
        this.user = user;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Integer state;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;


}
