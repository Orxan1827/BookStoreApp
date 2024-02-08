package com.example.bookstoreapp.dto;

import com.example.bookstoreapp.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserDto {

    private Long id;
    private String username;
    private Role role;
}
