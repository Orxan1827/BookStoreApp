package com.example.bookstoreapp.exception;

import lombok.NoArgsConstructor;

import static com.example.bookstoreapp.enums.ErrorCode.AUTHOR_NOT_FOUND;

@NoArgsConstructor
public class AuthorNotFoundException extends GenericException{

    public AuthorNotFoundException(String message) {
        super(AUTHOR_NOT_FOUND.getStatus(), AUTHOR_NOT_FOUND.getCode(),AUTHOR_NOT_FOUND.getMessage());
    }
}
