package com.example.bookstoreapp.entity;

import com.example.bookstoreapp.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int age;
    private String name;
    private String username;
    private String password;
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

}
