package com.example.bookstoreapp.dto.request;

import com.example.bookstoreapp.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SignUpRequest {

    @NotBlank
    @Pattern(regexp = "^[A-Z][a-zA-Z]*$", message = "Numbers and the first letter starting with a lowercase letter not allowed")
    private String name;

    @NotBlank
    private int age;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @Email(regexp = "^(.+)@(.+)$", message = "Email is wrong")
    private String email;

    @NotBlank
    private Role role;
}