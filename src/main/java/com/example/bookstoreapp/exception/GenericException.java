package com.example.bookstoreapp.exception;

import com.example.bookstoreapp.enums.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class GenericException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorCode;
    private String errorMessage;
}
