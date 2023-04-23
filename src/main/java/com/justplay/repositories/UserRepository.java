package com.justplay.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justplay.models.User;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);
    
}

