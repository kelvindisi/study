package com.devkiu.authentication.security;

import com.google.common.collect.Sets;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static com.devkiu.authentication.security.ApplicationUserPermission.STUDENT_READ;
import static com.devkiu.authentication.security.ApplicationUserPermission.STUDENT_WRITE;

public enum ApplicationUserRole {
    ADMIN(Sets.newHashSet(
            STUDENT_READ,
            STUDENT_WRITE
    )),
    LECTURER(
            Sets.newHashSet(
                    STUDENT_READ
            )
    );

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<? extends GrantedAuthority> getGrantedAuthorities() {
        Set<SimpleGrantedAuthority> authorities = permissions.stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toSet());
        authorities.add(new SimpleGrantedAuthority(this.name()));

        return authorities;
    }
}
