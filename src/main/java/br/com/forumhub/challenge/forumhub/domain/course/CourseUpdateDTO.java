package br.com.forumhub.challenge.forumhub.domain.course;

public record CourseUpdateDTO(
        String name,
        CategoryCourses categoryCourses
) {
}
