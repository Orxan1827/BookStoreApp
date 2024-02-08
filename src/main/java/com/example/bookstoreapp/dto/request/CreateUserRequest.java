package com.example.bookstoreapp.dto.request;

import com.example.bookstoreapp.enums.Role;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String email;

    @NotNull
    private Role userRoles;

    @NotBlank
    private String name;

    @Min(0)
    private int age;

    @NotBlank
    private String bookName;
}
