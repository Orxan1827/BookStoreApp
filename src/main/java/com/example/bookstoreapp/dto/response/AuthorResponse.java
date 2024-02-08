package com.example.bookstoreapp.dto.response;

import com.example.bookstoreapp.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorResponse {

    private Long id;
    private String name;
    private int age;
    private List<Book> books;
}
