package com.example.xapikey.service;


import com.example.xapikey.dto.UserDto;
import com.example.xapikey.entity.Authority;
import com.example.xapikey.entity.User;
import com.example.xapikey.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("excpetion loadUserByUsername " + username));
    }

    public void createUser(UserDto UserDto) {
        User newUser = User.builder()
                .username(UserDto.getUsername())
                .password(passwordEncoder.encode(UserDto.getPassword()))
                .authorities(List.of(Authority.builder().authority("USER").build()))
                .build();
        userRepository.save(newUser);
    }

    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<User> setApiKeyForUser(Long userId) {
        return userRepository.findById(userId).map(user -> {
            String apiKey = UUID.randomUUID().toString();
            user.setApiKey(apiKey);
            return userRepository.save(user);
        });
    }

    public Optional<User> findByApiKey(String apiKey) {
        return userRepository.findByApiKey(apiKey);
    }

}
