package lt.techin.cat_cafe_shop.repository;

import lt.techin.cat_cafe_shop.model.Reservation;
import lt.techin.cat_cafe_shop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findAllByUser(User user);
}
