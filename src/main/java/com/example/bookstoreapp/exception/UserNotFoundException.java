package com.example.bookstoreapp.exception;

import lombok.NoArgsConstructor;

import static com.example.bookstoreapp.enums.ErrorCode.USER_NOT_FOUND;

@NoArgsConstructor
public class UserNotFoundException extends GenericException{

    public UserNotFoundException(String message) {
        super(USER_NOT_FOUND.getStatus(), USER_NOT_FOUND.getCode(),USER_NOT_FOUND.getMessage());
    }
}
