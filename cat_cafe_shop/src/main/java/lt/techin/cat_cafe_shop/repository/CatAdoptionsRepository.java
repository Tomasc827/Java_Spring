package lt.techin.cat_cafe_shop.repository;

import lt.techin.cat_cafe_shop.model.AdoptionStatus;
import lt.techin.cat_cafe_shop.model.CatAdoption;
import lt.techin.cat_cafe_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CatAdoptionsRepository extends JpaRepository<CatAdoption,Long> {
    List<CatAdoption> findAllByUser(User user);

    List<CatAdoption> findAllByStatus(AdoptionStatus status);
}
