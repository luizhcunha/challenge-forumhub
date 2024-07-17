package br.com.forumhub.challenge.forumhub.domain.course;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @Autowired
    CourseRepository courseRepository;
    public Course updateCourse(CourseUpdateDTO courseUpdateDTO, Long id) {
        var course = courseRepository.getReferenceById(id);
        if(courseUpdateDTO.name() != null) {
            course.setName(courseUpdateDTO.name());
        }
        if(courseUpdateDTO.categoryCourses() != null) {
            course.setCategoryCourses(courseUpdateDTO.categoryCourses());
        }
        return course;
    }

    public void deleteCourse(Long id) {
        var course = courseRepository.findById(id);
        if(course.isPresent()) {
            courseRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Curso não encontrado.");
        }
    }
}
