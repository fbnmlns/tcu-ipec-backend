package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
