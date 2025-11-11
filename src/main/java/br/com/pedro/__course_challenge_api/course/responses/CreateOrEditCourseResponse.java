package br.com.pedro.__course_challenge_api.course.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateOrEditCourseResponse {
    private Boolean success;
}
