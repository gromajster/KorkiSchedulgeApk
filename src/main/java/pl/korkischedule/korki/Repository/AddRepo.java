package pl.korkischedule.korki.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.korkischedule.korki.Entity.Add;
import pl.korkischedule.korki.Entity.Category;
import pl.korkischedule.korki.Entity.TypeOfAdd;
import pl.korkischedule.korki.Entity.UserEntity;

import java.util.Set;

public interface AddRepo extends CrudRepository<Add, Long> {

    Set<Add> findAllByOwner(UserEntity owner);
    Set<Add> findAllByCategory(Category category);

    Set<Add> findAllByCategoryAndTypeOfAdd(Category category, TypeOfAdd typeOfAdd);

    Set<Add> findAllByOwnerAndTypeOfAdd(UserEntity owner, TypeOfAdd typeOfAdd);

    Set<Add> findAllByExpose(boolean expose);


}
