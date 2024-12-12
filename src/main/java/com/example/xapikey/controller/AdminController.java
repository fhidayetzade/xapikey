package com.example.xapikey.controller;

import com.example.xapikey.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String adminMethod() {
        return "Hello from admin api";
    }


    @PostMapping("/users/{userId}/apikey")
    public ResponseEntity<String> setApiKeyForUser(@PathVariable Long userId) {
        return userService.setApiKeyForUser(userId)
                .map(user -> ResponseEntity.ok("API key set successfully for user: " + user.getUsername()))
                .orElse(ResponseEntity.notFound().build());
    }
}