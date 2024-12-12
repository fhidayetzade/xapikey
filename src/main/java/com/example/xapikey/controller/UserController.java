package com.example.xapikey.controller;


import com.example.xapikey.dto.UserDto;
import com.example.xapikey.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/create")
    public String userCreate(@RequestBody UserDto UserDto) {
        userService.createUser(UserDto);
        return "User created";
    }



}
