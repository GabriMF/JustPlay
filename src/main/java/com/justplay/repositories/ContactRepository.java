package com.justplay.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.justplay.models.Contact;

public interface ContactRepository extends JpaRepository<Contact,Long> {
    
}
