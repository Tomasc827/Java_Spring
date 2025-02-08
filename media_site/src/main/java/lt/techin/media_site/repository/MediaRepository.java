package lt.techin.media_site.repository;

import lt.techin.media_site.model.Media;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MediaRepository extends JpaRepository<Media,Long> {
    boolean existsByTitle(String title);

    boolean existsByDescription(String description);
}
