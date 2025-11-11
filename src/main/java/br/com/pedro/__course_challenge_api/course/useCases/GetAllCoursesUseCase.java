package br.com.pedro.__course_challenge_api.course.useCases;

import br.com.pedro.__course_challenge_api.course.entity.CourseEntity;
import br.com.pedro.__course_challenge_api.course.repository.CourseRepository;
import br.com.pedro.__course_challenge_api.course.responses.GetAllCoursesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllCoursesUseCase {

    @Autowired
    CourseRepository courseRepository;

    public GetAllCoursesResponse execute(){
        List<CourseEntity> list =  this.courseRepository.findAll();
        return GetAllCoursesResponse
                .builder()
                .courses(list)
                .build();
    }
}
