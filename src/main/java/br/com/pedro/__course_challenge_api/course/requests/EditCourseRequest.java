package br.com.pedro.__course_challenge_api.course.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EditCourseRequest {
    @Pattern(regexp = "^(|.{2,50})", message = "O campo [name] deve ter entre 2 e 50 caracteres.")
    @Schema(example = "TailwindCSS")
    private String name = "";

    @Pattern(regexp = "^(|.{3,50})", message = "O campo [professor] deve ter entre 3 e 50 caracteres.")
    @Schema(example = "Pedro Sodré")
    private String professor = "";

    @Pattern(regexp = "(Back-end|Front-end|)", message = "O campo [category] deve ser Back-end ou Front-end.")
    @Schema(example = "Front-end")
    private String category = "";
}