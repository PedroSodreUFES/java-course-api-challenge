package br.com.pedro.__course_challenge_api.course.useCases;

import br.com.pedro.__course_challenge_api.course.entity.CourseEntity;
import br.com.pedro.__course_challenge_api.course.repository.CourseRepository;
import br.com.pedro.__course_challenge_api.exception.errors.CourseNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteCourseByIdUseCase {

    @Autowired
    CourseRepository courseRepository;

    public void execute(UUID uuid) throws CourseNotFound {
        CourseEntity entity = this.courseRepository.findById(uuid).orElseThrow(
                CourseNotFound::new
        );
        this.courseRepository.delete(entity);
    }
}
