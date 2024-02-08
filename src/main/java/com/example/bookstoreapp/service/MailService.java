package com.example.bookstoreapp.service;

import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.Student;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }

    public void createEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orxanrustamov1827@gmail.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        sendEmail(message);
    }

    public String notifySubscribedStudentsWithEmail(Author author, Book newbook) {
        List<Student> subscribedStudents = author.getFollowers();
        log.info(" " + subscribedStudents);
        for (Student student : subscribedStudents) {
            String email = student.getEmail();
            createEmail(email, "New Book Notification", newbook.toString());
        }
        return "Email sent successfully";
    }

    public String sendEmail(String email, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("orxanrustamov1827@gmail.com");
        message.setTo(email);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
        return email;
    }

    public void sendEmailList(List<String> emails, String subject, String text) {
        for (String to : emails) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("orxanrustamov1827@gmail.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            javaMailSender.send(message);
        }
    }
}
