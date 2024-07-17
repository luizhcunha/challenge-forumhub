package br.com.forumhub.challenge.forumhub.domain.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "courses")
@Entity(name = "Course")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CategoryCourses categoryCourses;

    public Course(String name, CategoryCourses categoryCourses) {
        this.name = name;
        this.categoryCourses = categoryCourses;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryCourses getCategoryCourses() {
        return categoryCourses;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategoryCourses(CategoryCourses categoryCourses) {
        this.categoryCourses = categoryCourses;
    }
}
