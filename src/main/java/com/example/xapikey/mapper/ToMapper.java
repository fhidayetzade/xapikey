package com.example.xapikey.mapper;

import com.example.xapikey.dto.TokenRequestDto;
import com.example.xapikey.entity.MD5Token;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToMapper {

    TokenRequestDto toTokenRequestDto(MD5Token token);
}
