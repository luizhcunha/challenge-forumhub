package br.com.forumhub.challenge.forumhub.domain.course;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;

public record CourseCreationDTO(
        @NotBlank
        String name,
        @Enumerated(EnumType.STRING)
        CategoryCourses categoryCourses
) {
}
