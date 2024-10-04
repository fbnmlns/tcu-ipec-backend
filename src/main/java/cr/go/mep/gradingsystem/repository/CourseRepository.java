package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByInstructorId(Long instructorId);
}
