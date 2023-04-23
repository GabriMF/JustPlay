package com.justplay.services;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.justplay.exception.ProfileNotFoundException;
import com.justplay.models.Profile;
import com.justplay.models.User;
import com.justplay.repositories.ProfileRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileService implements BaseService<Profile>  {

    private UserService userService;
    private ProfileRepository profileRepository;
    @Override
    public List<Profile> findAll() {
        return profileRepository.findAll();
    }

    @Override
    @Transactional
    public Page<Profile> findAll(Pageable pageable) {
        return profileRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Profile findById(Long id) {
        return profileRepository.findById(id)
        .orElseThrow(() -> new ProfileNotFoundException("Profile nor found with id: " + id));
    }

    @Override
    @Transactional
    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Profile profileDelete = findById(id);
        profileRepository.deleteById(profileDelete.getId());
    }

    @Transactional
    public Profile updateProfile(Long id, Profile profileDetails){
        Profile profile = findById(id);
        profile.setName(profileDetails.getName());
        profile.setInstagram(profileDetails.getInstagram());
        profile.setLocation(profileDetails.getLocation());
        profile.setSpotify(profileDetails.getSpotify());
        profile.setYoutube(profileDetails.getYoutube());
        return save(profile);
    }
    
    // @Transactional
    // public void addContact(Long id){
    //     Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    //     String currentUsername = authentication.getName();
    //     User user = userService.findByUsername(currentUsername);

    //     // user.getProfile().setContacts(id);
    //     Set<Long> contacts = user.getProfile().getContacts();
    //     contacts.add(id);

    //     System.out.println(user.getProfile().getContacts());

        

    // }
    
}
