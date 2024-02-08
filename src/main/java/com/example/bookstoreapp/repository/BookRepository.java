package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> getBooksByAuthor(Author author);
}
