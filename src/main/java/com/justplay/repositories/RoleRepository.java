package com.justplay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justplay.models.Role;

public interface RoleRepository extends JpaRepository<Role,Long>{ 
    
}
