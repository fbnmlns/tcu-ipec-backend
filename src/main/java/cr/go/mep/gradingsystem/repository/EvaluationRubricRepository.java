package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.EvaluationRubric;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvaluationRubricRepository extends JpaRepository<EvaluationRubric, Long> {
}
