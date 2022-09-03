package com.rock_papper_scissor.rock_papper_scissor.repositories;

import com.rock_papper_scissor.rock_papper_scissor.entities.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findByUserId(Long id);

    Long countByUserIdAndState(Long id, Integer status);
}
