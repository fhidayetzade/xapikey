package com.example.xapikey.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "md5_tokens")
@Data
public class MD5Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;
    private String consumer;
    private String username;
    private String password;
}

