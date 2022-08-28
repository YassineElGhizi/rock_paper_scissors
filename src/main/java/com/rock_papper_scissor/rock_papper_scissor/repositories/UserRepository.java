package com.rock_papper_scissor.rock_papper_scissor.repositories;

import com.rock_papper_scissor.rock_papper_scissor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByName(String name);
}
