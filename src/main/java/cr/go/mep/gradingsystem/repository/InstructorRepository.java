package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
