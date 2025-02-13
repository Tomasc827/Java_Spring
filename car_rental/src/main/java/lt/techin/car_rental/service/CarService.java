package lt.techin.car_rental.service;


import lt.techin.car_rental.dto.carDTO.CarMapper;
import lt.techin.car_rental.dto.carDTO.CarRequestDTO;
import lt.techin.car_rental.model.Car;
import lt.techin.car_rental.model.CarStatus;
import lt.techin.car_rental.repository.CarRepository;
import lt.techin.car_rental.validation.exception.CarRentedException;
import lt.techin.car_rental.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car addCar(CarRequestDTO dto) {
        Car car = CarMapper.toEntity(dto);
        car.setCarStatus(CarStatus.AVAILABLE);
        carRepository.save(car);
        return car;
    }
    public Car findCarById(long id) {
        return carRepository.findById(id).orElseThrow(() -> new NotFoundException("Car with id '" + id + "' does " +
                "not exist"));
    }

    public void removeCar(long id) {
        Car car = findCarById(id);
        if (car.getCarStatus() == CarStatus.RENTED) {
            throw new CarRentedException("Car with id '" + car.getId() + "' cannot be deleted, it is currently rented");
        }
        carRepository.delete(car);

    }

    public Car updateCar(CarRequestDTO dto, long id) {
        Car existingCar = findCarById(id);
        existingCar.setBrand(dto.brand());
        existingCar.setModel(dto.model());
        existingCar.setYear(dto.year());
        return carRepository.save(existingCar);
    }

    public String changeRentalStatus(long id) {
        Car existing = findCarById(id);
        if(existing.getCarStatus() == CarStatus.AVAILABLE) {
            existing.setCarStatus(CarStatus.RENTED);
        } else {
            existing.setCarStatus(CarStatus.AVAILABLE);
        }
        carRepository.save(existing);

        return "Car rental status with id '" + id + "' successfully changed to " + (existing.getCarStatus() == CarStatus.AVAILABLE ? "available" : "rented");
    }

    public List<Car> getAllCarsAdmin() {
        return new ArrayList<>(carRepository.findAll());
    }
    public List<Car> getAllCarsPublic() {
        return new ArrayList<>(carRepository.findAllByCarStatus(CarStatus.AVAILABLE));
    }


}

