package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.AttendanceStudent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceStudentRepository extends JpaRepository<AttendanceStudent, Long> {
}
