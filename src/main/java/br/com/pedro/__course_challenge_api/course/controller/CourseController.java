package br.com.pedro.__course_challenge_api.course.controller;

import br.com.pedro.__course_challenge_api.course.requests.CreateCourseRequest;
import br.com.pedro.__course_challenge_api.course.responses.CreateOrEditCourseResponse;
import br.com.pedro.__course_challenge_api.course.useCases.CreateCourseUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CreateCourseUseCase createCourseUseCase;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseRequest request){
        CreateOrEditCourseResponse response = createCourseUseCase.execute(request);
        return ResponseEntity.status(201).body(response);
    }
}
