package com.rock_papper_scissor.rock_papper_scissor.controllers;


import com.rock_papper_scissor.rock_papper_scissor.entities.User;
import com.rock_papper_scissor.rock_papper_scissor.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("ranking")
public class RankingController {

    private final UserRepository userRepository;

    public RankingController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping
//    public HttpEntity<HashMap<String, Object>> get_ranking(){
    public String get_ranking() {
        Optional<User> u = userRepository.findById(1L);
        if (u.isPresent()) {
            AtomicReference<Integer> wins = new AtomicReference<>(0);
            AtomicReference<Integer> draws = new AtomicReference<>(0);
            AtomicReference<Integer> loses = new AtomicReference<>(0);

            User uu = u.get();

            uu.get_wins().forEach(game -> {
                switch (game.getState()) {
                    case 1:
                        wins.updateAndGet(v -> v + 1);
                        break;
                    case 0:
                        draws.updateAndGet(v -> v + 1);
                        break;
                    case -1:
                        loses.updateAndGet(v -> v + 1);
                    default:
                        break;
                }
            });

            Integer score = (wins.get() * 3) + draws.get();
            HashMap<Object, Object> data = new HashMap<>();
            data.put("wins", wins);
            data.put("draws", draws);
            data.put("loses", loses);
            data.put("score", score);
            data.put("user_name", uu.getName());
            data.put("user_img", uu.getImg());

            System.out.println(data);
        }
        return "OK";
    }
}
