package com.example.bookstoreapp.enums;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    BOOK_NOT_FOUND("Book not found with given id","404", HttpStatus.NOT_FOUND.name()),

    AUTHOR_NOT_FOUND("Author not found with given id","404", HttpStatus.NOT_FOUND.name()),

    STUDENT_NOT_FOUND("Student not found with given id","404", HttpStatus.NOT_FOUND.name()),

    USER_NOT_FOUND("User not found with given id","404", HttpStatus.NOT_FOUND.name());

    private String message;
    private String code;
    private HttpStatus status;

    ErrorCode(String message, String code, String status) {
        this.message = message;
        this.code = code;
        this.status = HttpStatus.valueOf(status);
    }
}
