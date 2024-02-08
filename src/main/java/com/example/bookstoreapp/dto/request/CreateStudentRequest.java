package com.example.bookstoreapp.dto.request;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateStudentRequest {

    @Min(0)
    private Long studentId;

    @Min(0)
    private Long bookId;
}
