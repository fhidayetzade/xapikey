package com.example.xapikey.service;

import com.example.xapikey.dto.TokenRequestDto;
import com.example.xapikey.entity.MD5Token;
import com.example.xapikey.mapper.ToMapper;
import com.example.xapikey.repository.MD5TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MD5TokenService {

    private final ToMapper toMapper;

    @Autowired
    private MD5TokenRepository repository;

    public String saveToken(TokenRequestDto request) {
        String result = "";

        for (int i = 0; i < request.getToken().length(); i++) {
            result += request.getToken().charAt(i);
            if ((i + 1) % 8 == 0 && i + 1 != request.getToken().length()) {
                result += "-";
            }
        }
        if (!repository.existsByToken(result)) {
            MD5Token md5Token = new MD5Token();
            result.length();
            md5Token.setToken(result);
            md5Token.setConsumer(request.getConsumer());
            md5Token.setUsername(request.getUsername());
            md5Token.setPassword(request.getPassword());
            repository.save(md5Token);
            return "Token saved successfully.";
        } else {
            return "Token already exists in the database.";
        }
    }


}
