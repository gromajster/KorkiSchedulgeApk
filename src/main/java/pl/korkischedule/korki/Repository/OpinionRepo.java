package pl.korkischedule.korki.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.korkischedule.korki.Entity.Opinion;

public interface OpinionRepo extends CrudRepository<Opinion, Long> {
}
