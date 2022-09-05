package com.rock_papper_scissor.rock_papper_scissor.controllers;


import com.rock_papper_scissor.rock_papper_scissor.entities.User;
import com.rock_papper_scissor.rock_papper_scissor.repositories.UserRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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
    public List<HashMap<Object, Object>> get_ranking() {
        List<User> list_users = userRepository.findAll();
        List<HashMap<Object, Object>> response = new ArrayList<>();
        list_users.forEach(user -> {
            response.add(this.get_user_score(user));
        });

        Collections.sort(response, new Comparator<HashMap<Object, Object>>() {
            @Override
            public int compare(HashMap<Object, Object> o1, HashMap<Object, Object> o2) {
                return (-1) * Integer.compare((int) o1.get("score"), (int) o2.get("score"));
            }
        });
        return response;
    }

    public HashMap<Object, Object> get_user_score(User user) {
        AtomicReference<Integer> wins = new AtomicReference<>(0);
        AtomicReference<Integer> draws = new AtomicReference<>(0);
        AtomicReference<Integer> loses = new AtomicReference<>(0);

        user.get_wins().forEach(game -> {
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
        data.put("user_name", user.getName());
        data.put("user_img", user.getImg());
        return data;
    }
}
