package br.com.pedro.__course_challenge_api.exception.errors;

public class CourseAlreadyExist extends RuntimeException {
    public CourseAlreadyExist() {
        super("Course already exist.");
    }
}
