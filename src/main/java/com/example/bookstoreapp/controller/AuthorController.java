package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.dto.request.CreateBookRequest;
import com.example.bookstoreapp.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('AUTHOR')")
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;


    @DeleteMapping("/{author-id}/books/{book-id}")
    public void deleteBook(@Valid @PathVariable("author-id") Long authorId, @PathVariable("book-id") Long bookId) {
        authorService.deleteBook(authorId, bookId);
    }

    @PostMapping("/save")
    public ResponseEntity<String> createBook(@Valid @RequestBody CreateBookRequest request) {
        return ResponseEntity.ok(authorService.createBook(request));
    }
}
