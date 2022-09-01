package com.rock_papper_scissor.rock_papper_scissor.controllers;

import com.rock_papper_scissor.rock_papper_scissor.entities.Game;
import com.rock_papper_scissor.rock_papper_scissor.entities.User;
import com.rock_papper_scissor.rock_papper_scissor.repositories.GameRepository;
import com.rock_papper_scissor.rock_papper_scissor.repositories.UserRepository;
import com.rock_papper_scissor.rock_papper_scissor.requests.GameRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("game")
public class GameController {
    private final UserRepository userRepository;
    private final GameRepository gameRepository;

    @Autowired
    public GameController(UserRepository userRepository, GameRepository gameRepository) {
        this.userRepository = userRepository;
        this.gameRepository = gameRepository;
    }

    @PostMapping
    public HttpEntity<User> handle_game_score(@RequestBody GameRequest gameRequest, @RequestHeader("token") String token) {
        Optional<User> user = userRepository.findById(gameRequest.user_id);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(new User());
        } else {
            User u = user.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(u.getName(), token)) {
                System.out.println("NEW SKILSS ");
                System.out.println(gameRequest.game_score);
                System.out.println(gameRequest.game_score.getClass());
                gameRepository.save(new Game(gameRequest.game_score, u));
                return ResponseEntity.status(HttpStatus.CREATED).body(u);
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(new User());

        }

    }
}
