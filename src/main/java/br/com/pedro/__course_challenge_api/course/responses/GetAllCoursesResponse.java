package br.com.pedro.__course_challenge_api.course.responses;

import br.com.pedro.__course_challenge_api.course.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllCoursesResponse {
    List<CourseEntity> courses;
}
