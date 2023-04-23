package com.justplay.security;

import org.springframework.security.core.GrantedAuthority;

import com.justplay.models.Role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SecurityRole implements GrantedAuthority {

    private final Role role;

    @Override
    public String getAuthority() {
        return role.getRoleName();
    }
}
