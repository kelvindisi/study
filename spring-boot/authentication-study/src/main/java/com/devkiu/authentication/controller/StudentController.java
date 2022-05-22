package com.devkiu.authentication.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/students")
@PreAuthorize("hasAnyRole('ADMIN', 'LECTURER')")
public class StudentController {
    @GetMapping
    @PreAuthorize("hasAuthority('student:read')")
    public String getAllStudents() {
        return "all students";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public String addNewStudent() {
        return "added";
    }
}
