package br.com.pedro.__course_challenge_api.exception.errors;

public class CourseNotFound extends RuntimeException {
    public CourseNotFound() {
        super("Curso não encontrado");
    }
}
