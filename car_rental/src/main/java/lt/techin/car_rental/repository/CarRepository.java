package lt.techin.car_rental.repository;

import lt.techin.car_rental.model.Car;
import lt.techin.car_rental.model.CarStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findAllByCarStatus(CarStatus carStatus);
    Optional<Car> findById(long id);
}
