package com.devkiu.authentication.jwt;

import lombok.Data;

@Data
public class UsernamePasswordRequest {
    private String username;
    private String password;
}
