package com.devkiu.authentication.auth;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ApplicationUserDetailsService implements UserDetailsService {
    private final ApplicationUserDAO userDAO;

    public ApplicationUserDetailsService(@Qualifier("fake") ApplicationUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDAO.getUserByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException(
                        String.format("user with username %s not found", username)
                ));
    }
}
