package pl.korkischedule.korki.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.korkischedule.korki.Entity.UserRole;

@Repository
public interface UserRoleRepo extends CrudRepository<UserRole, Long> {

    UserRole findByRole(String role);
}
