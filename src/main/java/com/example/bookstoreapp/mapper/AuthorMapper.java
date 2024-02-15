package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.dto.request.CreateAuthorRequest;
import com.example.bookstoreapp.dto.request.CreateUserRequest;
import com.example.bookstoreapp.dto.request.SignUpRequest;
import com.example.bookstoreapp.dto.response.AuthorResponse;
import com.example.bookstoreapp.dto.response.UserResponse;
import com.example.bookstoreapp.entity.Author;
import com.example.bookstoreapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthorMapper {

    @Mapping(target = "id", ignore = true)
    Author mapRequestToAuthor(CreateAuthorRequest request);

    AuthorResponse mapEntityToResponse(Author author);

}

