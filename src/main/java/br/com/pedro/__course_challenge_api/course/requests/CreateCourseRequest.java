package br.com.pedro.__course_challenge_api.course.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateCourseRequest {

    @Length(min = 2, max = 50, message = "O campo [name] deve ter entre 2 e 20 caracteres.")
    @NotBlank
    private String name;

    @Length(min = 3, max = 50, message = "O campo [professor] deve ter entre 3 e 20 caracteres.")
    @NotBlank
    private String professor;

    @Pattern(regexp = "(Back-end|Front-end)", message = "O campo [category] deve ser Back-end ou Front-end.")
    @NotBlank
    private String category;
}
