package br.com.pedro.__course_challenge_api.exception.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageDTO {
    @Schema(example = "Mensagem de erro")
    private String error;
}
