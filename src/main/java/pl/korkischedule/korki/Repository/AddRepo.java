package pl.korkischedule.korki.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.korkischedule.korki.Entity.Add;
import pl.korkischedule.korki.Entity.Category;
import pl.korkischedule.korki.Entity.TypeOfAdd;
import pl.korkischedule.korki.Entity.User;

import java.util.Set;

public interface AddRepo extends CrudRepository<Add, Long> {

    Set<Add> findAllByOwner(User owner);
    Set<Add> findAllByCategory(Category category);

    Set<Add> findAllByCategoryAndTypeOfAdd(Category category, TypeOfAdd typeOfAdd);

    Set<Add> findAllByOwnerAndTypeOfAdd(User owner, TypeOfAdd typeOfAdd);

    Set<Add> findAllByExpose(boolean expose);


}
