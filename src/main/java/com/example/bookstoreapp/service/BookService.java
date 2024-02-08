package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.response.BookResponse;
import com.example.bookstoreapp.dto.response.BookResponseWithStudents;
import com.example.bookstoreapp.dto.response.BookReadersResponse;
import com.example.bookstoreapp.exception.BookNotFoundException;
import com.example.bookstoreapp.mapper.BookMapper;
import com.example.bookstoreapp.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(bookMapper::mapEntityToBookResponse)
                .collect(Collectors.toList());
    }

    private BookResponseWithStudents getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .map(bookMapper::mapEntityToResponseWithStudent)
                .orElseThrow(BookNotFoundException::new);

    }

    public List<BookReadersResponse> getReadersForBook(Long bookId) {
        var book = getBookById(bookId);
        return book.getStudents()
                .stream()
                .map(bookMapper::mapStudentsToBookReadersResponse)
                .collect(Collectors.toList());
    }
}
