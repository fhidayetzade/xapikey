package com.example.xapikey.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRequestDto {

    private String token;
    private String consumer;
    private String username;
    private String password;
}
