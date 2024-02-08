package com.example.bookstoreapp.mapper;

import com.example.bookstoreapp.dto.request.CreateUserRequest;
import com.example.bookstoreapp.dto.response.UserResponse;
import com.example.bookstoreapp.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    User mapRequestToUser(CreateUserRequest request);

    UserResponse mapEntityToResponse(User user);

//    User mapUpdateRequestToEntity(@MappingTarget Student student, UpdateStudentRequest request);
}