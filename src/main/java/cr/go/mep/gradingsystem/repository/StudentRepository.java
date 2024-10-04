package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Course;
import cr.go.mep.gradingsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query("SELECT s FROM Student s WHERE :course NOT MEMBER OF s.courses")
    List<Student> findStudentsNotInCourse(@Param("course") Course course);
}
