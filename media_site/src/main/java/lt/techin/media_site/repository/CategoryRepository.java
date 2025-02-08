package lt.techin.media_site.repository;

import lt.techin.media_site.model.Category;
import lt.techin.media_site.model.media_enum.CategoryEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Byte> {
    Optional<Category> findByCategory(CategoryEnum categoryEnum);
}
