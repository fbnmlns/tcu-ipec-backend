package cr.go.mep.gradingsystem.service;

import cr.go.mep.gradingsystem.repository.EvaluationRubricRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EvaluationRubricService {
    private final EvaluationRubricRepository evaluationRubricRepository;
}
