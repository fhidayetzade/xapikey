package com.example.xapikey.controller;

import com.example.xapikey.service.MD5TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/md5")
public class MD5TokenController {

    @Autowired
    private MD5TokenService service;

    @PostMapping("/save")
    public String saveToken(@RequestParam String token) {
        return service.saveToken(token);
    }
}
