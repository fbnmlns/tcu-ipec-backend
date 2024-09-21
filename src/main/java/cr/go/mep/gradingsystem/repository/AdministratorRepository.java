package cr.go.mep.gradingsystem.repository;

import cr.go.mep.gradingsystem.model.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministratorRepository extends JpaRepository<Administrator, Long>  {
}
