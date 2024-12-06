package com.example.xapikey.repository;

import com.example.xapikey.entity.MD5Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MD5TokenRepository extends JpaRepository<MD5Token, Long> {
    boolean existsByToken(String token);
}

