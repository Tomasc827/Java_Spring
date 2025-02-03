package lt.techin.Dealership.repository;

import lt.techin.Dealership.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car,Long> {
}
