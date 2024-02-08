package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.response.BookReadersResponse;
import com.example.bookstoreapp.dto.response.BookResponse;
import com.example.bookstoreapp.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @PreAuthorize("hasAnyAuthority('AUTHOR','STUDENT')")
    @GetMapping("/{bookId}")
    public ResponseEntity<List<BookReadersResponse>> getReadersForBook(@Valid @PathVariable Long bookId) {
        return ResponseEntity.ok(bookService.getReadersForBook(bookId));
    }

    @PreAuthorize("hasAnyAuthority('AUTHOR','STUDENT')")
    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }
}
