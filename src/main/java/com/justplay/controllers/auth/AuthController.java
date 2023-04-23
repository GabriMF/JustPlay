package com.justplay.controllers.auth;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.justplay.models.User;
import com.justplay.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
        UserService userService;

    @GetMapping("/login")
    public ResponseEntity<Map<String,String>> access() {
        
        var auth = SecurityContextHolder.getContext().getAuthentication();
        
        String userName = auth.getName();
        User user = userService.findByUsername(userName);
        Map<String,String> json = new HashMap<>();
        json.put("message", "Logged");
        json.put("role", auth.getAuthorities().iterator().next().toString());
        json.put("username", auth.getName());
        json.put("id", user.getId().toString());
        json.put("name", user.getProfile().getName());
        json.put("instagram", user.getProfile().getInstagram());
        json.put("location", user.getProfile().getLocation());
        json.put("spotify", user.getProfile().getSpotify());
        json.put("youtube", user.getProfile().getYoutube());


        return ResponseEntity.status(HttpStatus.ACCEPTED).body(json);
    }

    @GetMapping("/logout")
    public ResponseEntity<Map<String,String>> logout(HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        Map<String,String> json = new HashMap<>();
        json.put("message", "Logged out");
        return ResponseEntity.status(HttpStatus.OK).body(json);
    }
}
