package com.devkiu.authentication.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.devkiu.authentication.security.ApplicationUserRole.ADMIN;
import static com.devkiu.authentication.security.ApplicationUserRole.LECTURER;

@Repository("fake")
public class FakeApplicationUserDAOServiceImpl implements ApplicationUserDAO{
    private final PasswordEncoder passwordEncoder;

    public FakeApplicationUserDAOServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<? extends UserDetails> getUserByUsername(String username) {
        return users().stream()
                .filter(user -> username.equals(user.getUsername()))
                .findFirst();
    }

    private List<ApplicationUserDetails> users() {
        return Arrays.asList(
                new ApplicationUserDetails(
                        "admin",
                        passwordEncoder.encode("password"),
                        ADMIN.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new ApplicationUserDetails(
                        "lecturer",
                        passwordEncoder.encode("password"),
                        LECTURER.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
    }
}
