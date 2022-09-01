package com.rock_papper_scissor.rock_papper_scissor.entities;


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
    @Id
    @GeneratedValue
    private Long id;

    private Integer State;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


}
