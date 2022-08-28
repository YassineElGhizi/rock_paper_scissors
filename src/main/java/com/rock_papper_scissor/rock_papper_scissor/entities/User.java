package com.rock_papper_scissor.rock_papper_scissor.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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

}
