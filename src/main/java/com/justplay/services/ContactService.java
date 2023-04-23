package com.justplay.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.justplay.exception.ContactNotFoundException;
import com.justplay.exception.UserNotFoundException;
import com.justplay.models.Contact;
import com.justplay.models.User;
import com.justplay.repositories.ContactRepository;
import com.justplay.repositories.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ContactService implements BaseService<Contact>{
    
    private ContactRepository contactRepository;
    private UserRepository userRepository;
    
    @Transactional
    @Override
    public List<Contact> findAll() {
       return contactRepository.findAll();
    }

    @Transactional
    @Override
    public Page<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable);
    }

    @Transactional
    @Override
    public Contact findById(Long id) {
        return contactRepository.findById(id)
                    .orElseThrow(() -> new ContactNotFoundException("Contact not found with id: " + id));
    }

    @Transactional
    @Override
    public Contact save(Contact contact) {
        return contactRepository.save(contact);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
       Contact contactDeleted = findById(id);
       contactRepository.deleteById(contactDeleted.getId());
    }

    @Transactional
    public void addContactToUser(Long idContact){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername)
                .orElseThrow(() -> new UserNotFoundException("User not found with username: " + currentUsername));
        
        Contact contact = new Contact();
        contact.setProfile_id(idContact);
        contactRepository.save(contact);

        user.getProfile().getContacts().add(contact);
    }
}
