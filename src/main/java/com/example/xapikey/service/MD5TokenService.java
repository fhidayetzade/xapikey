package com.example.xapikey.service;

import com.example.xapikey.entity.MD5Token;
import com.example.xapikey.repository.MD5TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MD5TokenService {

    @Autowired
    private MD5TokenRepository repository;

    public String saveToken(String token) {
        // Check if the token already exists
        if (!repository.existsByToken(token)) {
            MD5Token md5Token = new MD5Token();
            md5Token.setToken(token);
            repository.save(md5Token);
            return "Token saved successfully.";
        } else {
            return "Token already exists in the database.";
        }
    }
}
