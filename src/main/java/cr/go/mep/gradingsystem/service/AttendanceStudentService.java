package cr.go.mep.gradingsystem.service;

import cr.go.mep.gradingsystem.repository.AttendanceStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AttendanceStudentService {
    private final AttendanceStudentRepository attendanceStudentRepository;
}
