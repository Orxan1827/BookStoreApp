package com.example.bookstoreapp.service;

import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.Student;
import com.example.bookstoreapp.entity.User;
import com.example.bookstoreapp.enums.Role;
import com.example.bookstoreapp.exception.AuthorNotFoundException;
import com.example.bookstoreapp.mapper.AuthorMapper;
import com.example.bookstoreapp.repository.AuthorRepository;
import com.example.bookstoreapp.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.bookstoreapp.enums.Role.AUTHOR;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class AuthorServiceTest {

    private AuthorRepository authorRepository;

    private AuthorMapper authorMapper;

    private MailService mailService;

    private BookRepository bookRepository;

    private AuthorService authorService;

    private Author author;

    private Book book;

    private User user;

    @BeforeEach
    void setUp() {
        authorMapper = Mockito.mock(AuthorMapper.class);
        mailService = Mockito.mock(MailService.class);
        authorRepository = Mockito.mock(AuthorRepository.class);
        authorService = Mockito.mock(AuthorService.class);

        authorService = new AuthorService(authorRepository, authorMapper, mailService);

        book = Book.builder()
                .id(2L)
                .name("Kitab")
                .createdAt(LocalDateTime.now())
                .build();

        author = Author.builder()
                .id(1L)
                .books(List.of(book))
                .build();

        user = User.builder()
                .id(1L)
                .role(AUTHOR)
                .build();
    }

    @Test
    void testWhenGetAuthorCalledById_itShouldReturnOptionalAuthor() {
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author result = authorService.getAuthor(1L);

        assertNotNull(result);
        assertEquals(author, result);

        Mockito.verify(authorRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testWhenGetAuthorCalledByIdIfDoesNotExist_itShouldReturnNotFoundException() {
        Mockito.when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(AuthorNotFoundException.class, () -> authorService.getAuthor(1L));

        Mockito.verify(authorRepository, Mockito.times(1)).findById(1L);
    }

    @Test
    void testWhenGetAllAuthorsCalled_itShouldReturnListOfAuthor() {
        Mockito.when(authorRepository.findAll()).thenReturn(List.of(author));

        List<Author> result = authorService.getAllAuthors();

        assertNotNull(result);
        assertEquals(List.of(author), result);

        Mockito.verify(authorRepository, Mockito.times(1)).findAll();
    }

    @Test
    void testWhenCreateAuthorCalledValidRequest() {
        Mockito.when(authorRepository.save(any(Author.class))).thenReturn(author);

        Mockito.verify(authorRepository, Mockito.times(0)).save(author);
    }
}