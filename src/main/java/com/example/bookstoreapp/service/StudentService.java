package com.example.bookstoreapp.service;

import com.example.bookstoreapp.dto.request.SignUpRequest;
import com.example.bookstoreapp.dto.response.StudentBookResponse;
import com.example.bookstoreapp.dto.response.StudentResponseWithBook;
import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.Student;
import com.example.bookstoreapp.entity.User;
import com.example.bookstoreapp.exception.AuthorNotFoundException;
import com.example.bookstoreapp.exception.GenericException;
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
    private final AuthorRepository authorRepository;
    private final MailService mailService;

    public Student getStudent(Long student_Id) {
        return studentRepository.findById(student_Id).orElseThrow(StudentNotFoundException::new);
    }

    public List<Student> getAllFollowers(Long student_Id) {
       return getStudent(student_Id)
               .getSubscriptions()
               .stream()
               .flatMap(author -> author.getFollowers().stream())
               .toList();
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
        Student student = new Student();
        student.setName(signUpRequest.getName());
        student.setAge(signUpRequest.getAge());
        student.setUser(user);
        student.setEmail(signUpRequest.getEmail());
        user.setStudent(studentRepository.save(student));
    }

    @Transactional
    public String followAuthor(Long author_Id, Long student_Id) {
        var author = authorRepository.findById(author_Id).orElseThrow(AuthorNotFoundException::new);
        var student = getStudent(student_Id);
        List<Author> authors = student.getSubscriptions();
        if (!(authors.contains(author))) {
            authors.add(author);
            student.setSubscriptions(authors);
            studentRepository.save(student);
            return "Subscription successfully!";
        }
        return "The author is already subscribed to!";
    }

    public String unfollowAuthor(Long student_Id) {
        Student student = getStudent(student_Id);
        List<Author> subscriptions = student.getSubscriptions();
        subscriptions.stream()
                .filter(author -> author.getFollowers()
                        .removeIf(student1 -> student1.getId().equals(student_Id)))
                .findFirst().ifPresent(authorRepository::save);

       return mailService.sendEmail("orxanrustamov1827@gmail.com", "subscribe notification", String.format("%s unsubscribed", student.getName()));
    }
}
