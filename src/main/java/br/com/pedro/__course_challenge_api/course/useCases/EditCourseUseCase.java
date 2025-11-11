package br.com.pedro.__course_challenge_api.course.useCases;

import br.com.pedro.__course_challenge_api.course.entity.CourseEntity;
import br.com.pedro.__course_challenge_api.course.repository.CourseRepository;
import br.com.pedro.__course_challenge_api.course.requests.CreateCourseRequest;
import br.com.pedro.__course_challenge_api.course.requests.EditCourseRequest;
import br.com.pedro.__course_challenge_api.exception.errors.CourseNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EditCourseUseCase {

    @Autowired
    CourseRepository courseRepository;

    public void execute(UUID id, EditCourseRequest request) throws CourseNotFound {
        CourseEntity entity = this.courseRepository.findById(id).orElseThrow(
                CourseNotFound::new
        );

        if(!request.getName().isEmpty()){
            entity.setNome(request.getName());
        }

        if(!request.getProfessor().isEmpty()){
            entity.setProfessor(request.getProfessor());
        }

        if(!request.getCategory().isEmpty()){
            entity.setCategory(request.getCategory());
        }

        this.courseRepository.save(entity);
    }
}
