package br.com.pedro.__course_challenge_api.course.controller;

import br.com.pedro.__course_challenge_api.course.requests.CreateCourseRequest;
import br.com.pedro.__course_challenge_api.course.responses.CreateOrEditCourseResponse;
import br.com.pedro.__course_challenge_api.course.responses.GetAllCoursesResponse;
import br.com.pedro.__course_challenge_api.course.useCases.CreateCourseUseCase;
import br.com.pedro.__course_challenge_api.course.useCases.GetAllCoursesUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CreateCourseUseCase createCourseUseCase;

    @Autowired
    GetAllCoursesUseCase getAllCoursesUseCase;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseRequest request){
        CreateOrEditCourseResponse response = this.createCourseUseCase.execute(request);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        GetAllCoursesResponse response = this.getAllCoursesUseCase.execute();
        return ResponseEntity.status(200).body(response);
    }
}
