package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student, Long>, JpaSpecificationExecutor<Author> {

}
