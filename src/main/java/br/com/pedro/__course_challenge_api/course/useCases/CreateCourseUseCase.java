package br.com.pedro.__course_challenge_api.course.useCases;

import br.com.pedro.__course_challenge_api.course.entity.CourseEntity;
import br.com.pedro.__course_challenge_api.course.repository.CourseRepository;
import br.com.pedro.__course_challenge_api.course.requests.CreateCourseRequest;
import br.com.pedro.__course_challenge_api.exception.errors.CourseAlreadyExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.Boolean;

@Service
public class CreateCourseUseCase {

    @Autowired
    CourseRepository courseRepository;

    public void execute(CreateCourseRequest request) throws CourseAlreadyExist{
        Boolean courseAlreadyExists = this.courseRepository
                .findByNome(request.getName())
                .isPresent();

        if(courseAlreadyExists.equals(true)){
            throw new CourseAlreadyExist();
        }

        CourseEntity entity = CourseEntity.builder()
                .nome(request.getName())
                .category(request.getCategory())
                .professor(request.getProfessor())
                .active(true)
                .build();

        this.courseRepository.save(entity);
    }
}
