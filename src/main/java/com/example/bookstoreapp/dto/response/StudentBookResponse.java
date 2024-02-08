package com.example.bookstoreapp.dto.response;

import com.example.bookstoreapp.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentBookResponse {

    private Long id;
    private String name;
}
