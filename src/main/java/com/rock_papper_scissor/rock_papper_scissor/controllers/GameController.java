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

import java.util.HashMap;
import java.util.List;
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


    @GetMapping
    public HttpEntity<HashMap<String, Object>> get_user_games(@RequestParam(name = "id") Long id) {
        List<Game> games = gameRepository.findByUserId(id);
        HashMap<String, Object> data = new HashMap<>();
        if (games.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(data);
        } else {
            Integer lose = Math.toIntExact(gameRepository.countByUserIdAndState(id, -1));
            Integer draw = Math.toIntExact(gameRepository.countByUserIdAndState(id, 0));
            Integer win = Math.toIntExact(gameRepository.countByUserIdAndState(id, 1));
            data.put("lose", lose);
            data.put("draw", draw);
            data.put("win", win);
            data.put("total_items", (long) games.size());
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
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
