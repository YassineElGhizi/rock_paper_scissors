package com.rock_papper_scissor.rock_papper_scissor.controllers;

import com.rock_papper_scissor.rock_papper_scissor.entities.User;
import com.rock_papper_scissor.rock_papper_scissor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("login")
public class LoginController {

    private final UserRepository userRepository;

    @Autowired
    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping
    public HttpEntity<User> login(@RequestBody User user) {
        User db_user = userRepository.findByName(user.getName());
        if (db_user != null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            if (encoder.matches(user.getPassword(), db_user.getPassword())) {
                db_user.setToken(encoder.encode(user.getName()));
                userRepository.save(db_user);
                return ResponseEntity.status(HttpStatus.CREATED).body(db_user);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(user);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(user);
        }
    }
}
