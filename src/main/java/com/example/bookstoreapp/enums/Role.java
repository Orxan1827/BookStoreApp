package com.example.bookstoreapp.enums;

import lombok.Getter;

@Getter
public enum Role {
    AUTHOR("AUTHOR"),STUDENT("STUDENT");

    private String value;

    Role(String value) {
        this.value = value;
    }
}
