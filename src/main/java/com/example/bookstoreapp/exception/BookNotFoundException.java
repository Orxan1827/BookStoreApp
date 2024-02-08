package com.example.bookstoreapp.exception;

import lombok.NoArgsConstructor;
import static com.example.bookstoreapp.enums.ErrorCode.*;

@NoArgsConstructor
public class BookNotFoundException extends GenericException{

    public BookNotFoundException(String message) {
        super(BOOK_NOT_FOUND.getStatus(), BOOK_NOT_FOUND.getCode(),BOOK_NOT_FOUND.getMessage());
    }
}
