package com.rock_papper_scissor.rock_papper_scissor.repositories;

import com.rock_papper_scissor.rock_papper_scissor.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {
}
