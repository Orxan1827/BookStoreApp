package com.example.bookstoreapp.dto.response;

import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookResponseWithStudents {

    private Long id;
    private String name;
    private List<Student> students;
}
