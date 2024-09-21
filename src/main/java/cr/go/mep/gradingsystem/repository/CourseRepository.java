package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
