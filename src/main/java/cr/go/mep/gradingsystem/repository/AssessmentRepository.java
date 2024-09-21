package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AssessmentRepository extends JpaRepository<Assessment, Long> {
}
