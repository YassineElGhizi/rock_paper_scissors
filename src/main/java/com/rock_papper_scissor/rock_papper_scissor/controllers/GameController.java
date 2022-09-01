package com.rock_papper_scissor.rock_papper_scissor.controllers;

import com.rock_papper_scissor.rock_papper_scissor.entities.User;
import com.rock_papper_scissor.rock_papper_scissor.repositories.GameRepository;
import com.rock_papper_scissor.rock_papper_scissor.repositories.UserRepository;
import com.rock_papper_scissor.rock_papper_scissor.requests.GameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("test")
public class GameController {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public GameController(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @PostMapping
    public HttpEntity<GameRequest> handle_game_score(@RequestBody GameRequest gameRequest) {
        System.out.println("recivied =>" + gameRequest.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(gameRequest);
    }
}
