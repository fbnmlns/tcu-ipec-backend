package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
