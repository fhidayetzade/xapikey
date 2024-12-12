package com.example.xapikey.mapper;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ToMapper {

    TokenRequestDto toTokenRequestDto(MD5Token token);
}
