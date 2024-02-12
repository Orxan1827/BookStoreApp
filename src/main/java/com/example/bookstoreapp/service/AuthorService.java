package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.request.CreateBookRequest;
import com.example.bookstoreapp.dto.request.SignUpRequest;
import com.example.bookstoreapp.dto.response.AuthorResponse;
import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.User;
import com.example.bookstoreapp.exception.AuthorNotFoundException;
import com.example.bookstoreapp.mapper.AuthorMapper;
import com.example.bookstoreapp.repository.AuthorRepository;
import com.example.bookstoreapp.repository.BookRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {
    private final AuthorRepository authorRepository;

    private final AuthorMapper authorMapper;
    private final MailService mailService;

    private final BookRepository bookRepository;

    public Author getAuthor(Long author_Id) {
        return authorRepository.findById(author_Id).orElseThrow(AuthorNotFoundException::new);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public List<AuthorResponse> getSearchForSpecification(String name) {
        Specification<Author> spec = (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("name"), name);
         return authorRepository.findAll((Sort) spec).stream().map(authorMapper::mapEntityToResponse).toList();
    }

    public void createAuthor(SignUpRequest signUpRequest, User user) {
        Author author = new Author();
        author.setAge(signUpRequest.getAge());
        author.setName(signUpRequest.getName());
        author.setUser(user);
        user.setAuthor(authorRepository.save(author));
    }

    public void deleteBook(Long authorId, Long bookId) {
        authorRepository.findById(authorId)
                .stream()
                .filter(author -> author.getBooks()
                        .removeIf(book -> book.getId().equals(bookId)))
                .findFirst()
                .map(authorRepository::save)
                .orElseThrow(() -> new RuntimeException("dosdmsl"));
    }

    @Transactional
    public String createBook(CreateBookRequest request) {
        var author = authorRepository.findById(request.getId()).orElseThrow();
        Book book = new Book();
        book.setName(request.getBookName());
        book.setAuthor(author);
        bookRepository.save(book);
        return mailService.notifySubscribedStudentsWithEmail(author, book);

//        book.setAuthor(author);
//        author.getBooks().add(book);
//        bookRepository.save(book);
    }
}
