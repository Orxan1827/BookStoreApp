package com.example.bookstoreapp.exception;

import lombok.NoArgsConstructor;

import static com.example.bookstoreapp.enums.ErrorCode.STUDENT_NOT_FOUND;
@NoArgsConstructor
public class StudentNotFoundException extends GenericException{

    public StudentNotFoundException(String message) {
        super(STUDENT_NOT_FOUND.getStatus(), STUDENT_NOT_FOUND.getCode(),STUDENT_NOT_FOUND.getMessage());
    }
}
