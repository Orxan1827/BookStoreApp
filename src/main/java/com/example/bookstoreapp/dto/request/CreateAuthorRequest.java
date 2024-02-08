package com.example.bookstoreapp.dto.request;

import com.example.bookstoreapp.entity.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAuthorRequest {

    @NotBlank
    private String name;

    @NotBlank
    private int age;

    @NotNull
    private List<Book> books;

}
