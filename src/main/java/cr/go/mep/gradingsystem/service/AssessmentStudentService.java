package cr.go.mep.gradingsystem.service;

import cr.go.mep.gradingsystem.repository.AssessmentStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AssessmentStudentService {
    private final AssessmentStudentRepository assessmentStudentRepository;
}
