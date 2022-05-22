package com.devkiu.authentication.auth;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface ApplicationUserDAO {
    Optional<? extends UserDetails> getUserByUsername(String username);
}
