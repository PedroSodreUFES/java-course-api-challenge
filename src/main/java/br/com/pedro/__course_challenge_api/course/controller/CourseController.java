package br.com.pedro.__course_challenge_api.course.controller;

import br.com.pedro.__course_challenge_api.course.requests.CreateCourseRequest;
import br.com.pedro.__course_challenge_api.course.requests.EditCourseRequest;
import br.com.pedro.__course_challenge_api.course.responses.GetAllCoursesResponse;
import br.com.pedro.__course_challenge_api.course.useCases.*;
import br.com.pedro.__course_challenge_api.exception.dto.MessageDTO;
import br.com.pedro.__course_challenge_api.exception.errors.CourseAlreadyExist;
import br.com.pedro.__course_challenge_api.exception.errors.CourseNotFound;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/course")
@Tag(name = "Curso", description = "Rotas relacionadas aos cursos.")
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
    @Operation(summary = "Cadastro de curso.", description = "Esse endpoint é responsável por cadastrar novos cursos.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Curso criado com sucesso.", content = @Content),
            @ApiResponse(responseCode = "409", content = { @Content(schema = @Schema(implementation = MessageDTO.class))}, description = "Curso já cadastrado.")
    })
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
    @Operation(summary = "Todos os cursos cadastrados.", description = "Essa rota é responsável por pegar todos os cursos cadastrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = GetAllCoursesResponse.class))}, description = "Sucesso")
    })
    public ResponseEntity<Object> getAll(){
        GetAllCoursesResponse response = this.getAllCoursesUseCase.execute();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deletar um curso", description = "Essa rota é responsável por apagar um curso cadastrado.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = @Content, description = "Apagado com sucesso."),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = MessageDTO.class))}, description = "Curso não encontrado."),
    })
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
    @Operation(summary = "Atualizar parcialmente um curso", description = "Essa rota é responsável por contradizer o campo de ativado de um curso.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = @Content, description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = MessageDTO.class))}, description = "Curso não encontrado."),
    })
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
    @Operation(summary = "Atualizar um curso", description = "Essa rota é responsável por atualizar um curso.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", content = @Content, description = "Atualizado com sucesso."),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = MessageDTO.class))}, description = "Curso não encontrado."),
    })
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
