package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.response.StudentBookResponse;
import com.example.bookstoreapp.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('STUDENT')")
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/message")
    public String message() {
        return "StudentController";
    }

    @GetMapping("/{student-id}")
    public ResponseEntity<List<StudentBookResponse>> getBooksForStudent(@Valid @PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(studentService.getAllReadingBooksForStudent(studentId));
    }

    @PostMapping("/add/{student-id}/books/{book-id}")
    public void addBookToStudent(@Valid @PathVariable("student-id") Long studentId, @Valid @PathVariable("book-id") Long bookId) {
        studentService.addBookToStudent(studentId, bookId);
    }

    @PostMapping("/follow/{author-id}/students/{student-id}")
    public ResponseEntity<String> followAuthor(@PathVariable("author-id") Long authorId, @PathVariable("student-id") Long studentId) {
        return ResponseEntity.ok(studentService.followAuthor(authorId, studentId));
    }

    @DeleteMapping("/unfollow/{author-id}/students/{student-id}")
    public ResponseEntity<String> unfollowAuthor(@PathVariable("student-id") Long studentId, @PathVariable("author-id") Long authorId) {
        return ResponseEntity.ok(studentService.unfollowAuthor(studentId, authorId));
    }
}
