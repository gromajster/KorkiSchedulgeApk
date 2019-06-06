package pl.korkischedule.korki.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.korkischedule.korki.Entity.UserEntity;
import pl.korkischedule.korki.Entity.YoutubeVideo;

import java.util.Set;

public interface YoutubeVideoRepo extends CrudRepository<YoutubeVideo, Long> {
    Set<YoutubeVideo> findAllByOwnerVideo(UserEntity owner);
}
