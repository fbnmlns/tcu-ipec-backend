package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
