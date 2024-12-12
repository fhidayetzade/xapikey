package com.example.xapikey.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class Authority implements GrantedAuthority {

    private String authority;
}
