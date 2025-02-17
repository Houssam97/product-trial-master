package com.alten.controller;

import com.alten.models.User;
import com.alten.security.JwtUtil;
import com.alten.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
public class AccountController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AccountController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/account")
    public ResponseEntity<User> createAccount(@RequestBody User user) {
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/token")
    public ResponseEntity<Map<String, String>> generateToken(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        User user = userService.getUserByEmail(email);

        if (user == null || !user.getPassword().equals(password)) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
