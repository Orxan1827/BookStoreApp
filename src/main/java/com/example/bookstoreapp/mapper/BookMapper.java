package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.dto.request.CreateBookRequest;
import com.example.bookstoreapp.dto.response.BookReadersResponse;
import com.example.bookstoreapp.dto.response.BookResponse;
import com.example.bookstoreapp.dto.response.StudentBookResponse;
import com.example.bookstoreapp.dto.response.BookResponseWithStudents;
import com.example.bookstoreapp.entity.Book;
import com.example.bookstoreapp.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BookMapper {

    @Mapping(target = "id", ignore = true)
    Book mapRequestToBook(CreateBookRequest request);

    StudentBookResponse mapEntityToResponse(Book book);

    BookResponse mapEntityToBookResponse(Book book);

    BookResponseWithStudents mapEntityToResponseWithStudent(Book book);

    BookReadersResponse mapStudentsToBookReadersResponse(Student student);


}
