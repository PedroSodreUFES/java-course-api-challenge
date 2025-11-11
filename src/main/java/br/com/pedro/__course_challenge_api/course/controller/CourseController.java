package br.com.pedro.__course_challenge_api.course.controller;

import br.com.pedro.__course_challenge_api.course.requests.CreateCourseRequest;
import br.com.pedro.__course_challenge_api.course.requests.EditCourseRequest;
import br.com.pedro.__course_challenge_api.course.responses.GetAllCoursesResponse;
import br.com.pedro.__course_challenge_api.course.useCases.*;
import br.com.pedro.__course_challenge_api.exception.dto.MessageDTO;
import br.com.pedro.__course_challenge_api.exception.errors.CourseAlreadyExist;
import br.com.pedro.__course_challenge_api.exception.errors.CourseNotFound;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    CreateCourseUseCase createCourseUseCase;

    @Autowired
    GetAllCoursesUseCase getAllCoursesUseCase;

    @Autowired
    DeleteCourseByIdUseCase deleteCourseByIdUseCase;

    @Autowired
    PatchCourseUseCase patchCourseUseCase;

    @Autowired
    EditCourseUseCase editCourseUseCase;

    @PostMapping()
    public ResponseEntity<Object> create(@Valid @RequestBody CreateCourseRequest request){
        try {
            this.createCourseUseCase.execute(request);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (CourseAlreadyExist ex) {
            MessageDTO errorMessage = new MessageDTO(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errorMessage);
        }
    }

    @GetMapping()
    public ResponseEntity<Object> getAll(){
        GetAllCoursesResponse response = this.getAllCoursesUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable String id){
        try {
            this.deleteCourseByIdUseCase.execute(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        } catch (CourseNotFound e) {
            MessageDTO errorMessage = new MessageDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PatchMapping("/{id}/active")
    public ResponseEntity<Object> toggleActive(@PathVariable String id){
        try {
            this.patchCourseUseCase.execute(UUID.fromString(id));
            return ResponseEntity.noContent().build();
        } catch (CourseNotFound e){
            MessageDTO errorMessage = new MessageDTO(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(@PathVariable String id, @Valid @RequestBody EditCourseRequest request){
        try {
            this.editCourseUseCase.execute(UUID.fromString(id), request);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (CourseNotFound ex){
            MessageDTO errorMessage = new MessageDTO(ex.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }
}
