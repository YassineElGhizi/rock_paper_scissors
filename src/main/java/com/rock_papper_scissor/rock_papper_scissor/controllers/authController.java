package com.rock_papper_scissor.rock_papper_scissor.controllers;

import com.rock_papper_scissor.rock_papper_scissor.entities.User;
import com.rock_papper_scissor.rock_papper_scissor.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("auth")
public class authController {
    private final UserRepository userRepository;

    @Autowired
    public authController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public HttpEntity<User> register(@RequestBody User user) {
        User db_user = userRepository.findByName(user.getName());
        if (db_user == null) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encrypted_password = encoder.encode(user.getPassword());
            user.setPassword(encrypted_password);
            System.out.println("Saving :" + user.toString());
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(user);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        }
    }


}
