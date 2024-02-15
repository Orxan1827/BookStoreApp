package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.TokenResponseDto;
import com.example.bookstoreapp.dto.UserDto;
import com.example.bookstoreapp.dto.request.LoginRequest;
import com.example.bookstoreapp.dto.request.SignUpRequest;
import com.example.bookstoreapp.entity.User;
import com.example.bookstoreapp.enums.ErrorCode;
import com.example.bookstoreapp.enums.Role;
import com.example.bookstoreapp.exception.GenericException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final TokenService tokenService;
    private final StudentService studentService;
    private final AuthorService authorService;
    private final PasswordEncoder encoder;

    public TokenResponseDto login(LoginRequest loginRequest) {
        try {
            Authentication auth = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            return TokenResponseDto
                    .builder()
                    .accessToken(tokenService.generateToken(auth))
                    .user(userService.findUser(loginRequest.getUsername()))
                    .build();
        } catch (final BadCredentialsException badCredentialsException) {
            throw GenericException.builder()
                    .httpStatus(HttpStatus.NOT_FOUND)
                    .errorCode(ErrorCode.USER_NOT_FOUND.getCode())
                    .errorMessage("Invalid Username or Password").build();
        }
    }

    @Transactional
    public UserDto signup(SignUpRequest signUpRequest) {
        var isAllReadyRegistered = userService.existsByUsername(signUpRequest.getUsername());

        if (isAllReadyRegistered) {
            throw GenericException.builder().httpStatus(HttpStatus.FOUND)
                    .errorMessage("Username: " + signUpRequest.getUsername() + "is already used").build();
        }

        var user = User.builder()
                .name(signUpRequest.getName())
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .role(signUpRequest.getRole())
                .email(signUpRequest.getEmail())
                .age(signUpRequest.getAge())
                .build();

        if (Role.STUDENT.equals(signUpRequest.getRole())) {
            studentService.creatStudent(signUpRequest, user);
        } else if (Role.AUTHOR.equals(signUpRequest.getRole())) {
            authorService.createAuthor(signUpRequest, user);
        }
        User fromDb = userService.create(user);

        return UserDto.builder()
                .id(fromDb.getId())
                .username(fromDb.getUsername())
                .role(fromDb.getRole())
                .build();

    }
}
