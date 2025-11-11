package br.com.pedro.__course_challenge_api.course.repository;

import br.com.pedro.__course_challenge_api.course.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID> {
}
