package com.justplay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justplay.models.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long>{
    
}
