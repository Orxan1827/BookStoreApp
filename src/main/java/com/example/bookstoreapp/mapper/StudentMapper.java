package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.dto.request.CreateStudentRequest;
import com.example.bookstoreapp.dto.response.BookReadersResponse;
import com.example.bookstoreapp.dto.response.StudentBookResponse;
import com.example.bookstoreapp.dto.response.StudentResponseWithBook;
import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student mapRequestToStudent(CreateStudentRequest request);

    BookReadersResponse mapEntityToResponse(Student student);

    StudentResponseWithBook mapEntityToResponseWithBook(Student student);

    StudentBookResponse mapBooksToStudentBookResponse(Book book);
}
