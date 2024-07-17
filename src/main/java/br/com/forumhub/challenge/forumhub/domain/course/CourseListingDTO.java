package br.com.forumhub.challenge.forumhub.domain.course;

public record CourseListingDTO(Long id, String name, CategoryCourses categoryCourses) {
    public CourseListingDTO(Course course) {
        this(course.getId(), course.getName(), course.getCategoryCourses());
    }
}
