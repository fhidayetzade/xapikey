package com.example.xapikey.controller;

import com.example.xapikey.dto.TokenRequestDto;
import com.example.xapikey.service.MD5TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/md5")
public class MD5TokenController {

    @Autowired
    private MD5TokenService service;

    @PostMapping("/save")
    public String saveToken(@RequestBody TokenRequestDto requestDto) {
        return service.saveToken(requestDto);
    }
}
