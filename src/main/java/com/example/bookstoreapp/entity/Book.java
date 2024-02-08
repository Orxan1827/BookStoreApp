package com.example.bookstoreapp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY)
    @ToString.Exclude
    private List<Student> students;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    @ToString.Exclude
    private Author author;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
