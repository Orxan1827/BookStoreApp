package com.example.bookstoreapp.service;

import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.Student;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledTaskService {

    private final MailService mailService;
    private final AuthorService authorService;

    @Scheduled(cron = "0 * * * * ?")
    @Transactional
    public void checkAndSendNotifications() {

        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(1);
        var author = authorService.getAllAuthors().stream().findAny().get();
        var followers = author.getFollowers();
        var books = author.getBooks();
        log.info("followers {}", followers);
        for (Book book : books) {
            LocalDateTime createdAt = book.getCreatedAt();
            if (createdAt.isAfter(fiveMinutesAgo)) {
                for (Student student : followers) {
                    String email = student.getEmail();
                    mailService.createEmail(email, "New Book Notification", book.toString());
                }
            }
        }
    }
}