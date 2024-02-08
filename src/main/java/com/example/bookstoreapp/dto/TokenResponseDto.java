package com.example.bookstoreapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class TokenResponseDto {

    private String accessToken;
    private UserDto user;
}
