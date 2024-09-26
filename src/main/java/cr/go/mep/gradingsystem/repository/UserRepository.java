package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findUserByUsername(String login);
}
