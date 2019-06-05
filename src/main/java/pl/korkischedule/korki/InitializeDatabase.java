package pl.korkischedule.korki;


import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import pl.korkischedule.korki.Entity.UserRole;
import pl.korkischedule.korki.Repository.OpinionRepo;
import pl.korkischedule.korki.Repository.UserRepo;
import pl.korkischedule.korki.Repository.UserRoleRepo;

@Service
@AllArgsConstructor
public class InitializeDatabase {

    private final UserRoleRepo userRoleRepo;
    private final OpinionRepo opinionRepo;
    private final UserRepo userRepo;

    @EventListener(ApplicationReadyEvent.class)
    public void addRoles() {
        try {
            userRoleRepo.save(new UserRole(1L, "ROLE_USER", "default user role"));
            userRoleRepo.save(new UserRole(2L, "ROLE_ADMIN", "admin role"));

        } catch (Exception ex) {
            System.out.println("Already in database");
        }
    }
}
