package com.example.xapikey.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyService {

    @Value("${api.key}")
    private String apiKey;

    @Value("${api.secret}")
    private String apiSecret;

    public boolean isValidApiKey(String key, String secret) {
        return apiKey.equals(key) && apiSecret.equals(secret);
    }
}