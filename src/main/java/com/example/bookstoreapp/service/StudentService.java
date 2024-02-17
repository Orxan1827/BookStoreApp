package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.request.SignUpRequest;
import com.example.bookstoreapp.dto.response.StudentBookResponse;
import com.example.bookstoreapp.dto.response.StudentResponseWithBook;
import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.Student;
import com.example.bookstoreapp.entity.User;
import com.example.bookstoreapp.exception.AuthorNotFoundException;
import com.example.bookstoreapp.exception.StudentNotFoundException;
import com.example.bookstoreapp.mapper.StudentMapper;
import com.example.bookstoreapp.repository.AuthorRepository;
import com.example.bookstoreapp.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentService {

    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final MailService mailService;

    protected Student getStudent(Long studentId) {
        return studentRepository
                .findById(studentId)
                .orElseThrow(StudentNotFoundException::new);
    }

    private StudentResponseWithBook getStudentById(Long studentId) {
        return studentRepository.findById(studentId)
                .map(studentMapper::mapEntityToResponseWithBook)
                .orElseThrow(RuntimeException::new);
    }

    public List<StudentBookResponse> getAllReadingBooksForStudent(Long studentId) {
        var student = getStudent(studentId);
        return student.getBooks().stream()
                .map(studentMapper::mapBooksToStudentBookResponse)
                .collect(Collectors.toList());
    }

    public void addBookToStudent(Long studentId, Long bookId) {
        var student = getStudent(studentId);
        var book = new Book();
        book.setId(bookId);
        student.getBooks().add(book);
        studentRepository.save(student);
    }

    public void creatStudent(SignUpRequest signUpRequest, User user) {
        Student student = Student.builder()
                .name(signUpRequest.getName())
                .age(signUpRequest.getAge())
                .user(user)
                .email(signUpRequest.getEmail())
                .build();
        studentRepository.save(student);
    }

    @Transactional
    public String followAuthor(Long authorId, Long studentId) {
        var student = getStudent(studentId);
        var author = new Author();
        author.setId(authorId);
        List<Author> authors = student.getSubscriptions();
        if (!(authors.contains(author))) {
            authors.add(author);
            student.setSubscriptions(authors);
            studentRepository.save(student);
            return "Subscription successfully!";
        }
        return "The author is already subscribed to!";
    }

    @Transactional
    public String unfollowAuthor(Long studentId, Long authorId) {
        Student student = getStudent(studentId);

        Author authorToUnfollow = student.getSubscriptions().stream()
                .filter(author -> author.getId().equals(authorId))
                .findFirst()
                .orElseThrow(() -> new AuthorNotFoundException("Author not found with id: " + authorId));

        boolean removed = authorToUnfollow
                .getFollowers()
                .removeIf(follower -> follower.getId().equals(studentId));
        if (!removed) {
            return "Student was not following the author with id: " + authorId;
        }

        student.getSubscriptions().remove(authorToUnfollow);
        studentRepository.save(student);

        String emailContent = String.format("%s unsubscribed from author with id: %d", student.getName(), authorId);
        mailService.sendEmail("orxanrustamov1827@gmail.com", "subscribe notification", emailContent);
        return emailContent;
    }
}
