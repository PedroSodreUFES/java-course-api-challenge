package br.com.pedro.__course_challenge_api.course.useCases;

import br.com.pedro.__course_challenge_api.course.entity.CourseEntity;
import br.com.pedro.__course_challenge_api.course.repository.CourseRepository;
import br.com.pedro.__course_challenge_api.course.requests.CreateCourseRequest;
import br.com.pedro.__course_challenge_api.course.responses.CreateOrEditCourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseUseCase {

    @Autowired
    CourseRepository courseRepository;

    public CreateOrEditCourseResponse execute(CreateCourseRequest request){
        CourseEntity entity = CourseEntity.builder()
                .nome(request.getName())
                .category(request.getCategory())
                .professor(request.getProfessor())
                .active(true)
                .build();

        this.courseRepository.save(entity);


        return CreateOrEditCourseResponse.builder()
                .success(true)
                .build();
    }
}
