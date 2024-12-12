package com.example.xapikey.config;

import com.example.xapikey.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommonAuthService implements AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final ApiKeyService apiKeyService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest request) {
        String header = request.getHeader("Authorization");

        if (header == null) {
            return Optional.empty();
        }


        if (header.startsWith("API ")) {
            return getApiKeyAuthentication(header.substring(3));
        }

        return Optional.empty();
    }



    private Optional<Authentication> getApiKeyAuthentication(String apiKeyHeader) {
        String[] parts = apiKeyHeader.split(":");
        if (parts.length == 2) {
            String apiKey = parts[0];
            String apiSecret = parts[1];

            log.info("API Key: {}, API Secret: {}", apiKey, apiSecret);

            if (apiKeyService.isValidApiKey(apiKey, apiSecret)) {
                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("USER"));
                return Optional.of(new UsernamePasswordAuthenticationToken(null, apiKey, authorities));
            }
        }
        return Optional.empty();
    }
}
