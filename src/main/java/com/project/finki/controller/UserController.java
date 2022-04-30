package com.project.finki.controller;

import com.project.finki.model.User;
import com.project.finki.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public String addNewUser(@RequestBody User user) {
        String password = user.getPassword();
        String encryptPassword = passwordEncoder.encode(password);
        user.setPassword(encryptPassword);
        userRepository.save(user);
        return "successfully added new user";
    }
}
