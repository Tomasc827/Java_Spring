package lt.techin.Dealership.service;


import lt.techin.Dealership.CarNotFoundException;
import lt.techin.Dealership.dto.CarDTO;
import lt.techin.Dealership.model.Car;
import lt.techin.Dealership.model.VIN;
import lt.techin.Dealership.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }


    public void saveCar(Car car) {
        carRepository.save(car);
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> findCarById(long id) {
        return carRepository.findById(id);
    }

    public void deleteCarById(long id) {
        carRepository.deleteById(id);
    }

    public Car updatedCarWithVIN(long id, CarDTO carDTO) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
                    car.setYear(carDTO.year());
                    car.setMake(carDTO.make());
                    car.setModel(carDTO.model());
                    car.setEngineType(carDTO.engineType());

                    if(carDTO.vin() != null) {
                        VIN vin = car.getVin();
                        if(vin == null) {
                            vin = new VIN();
                            car.setVin(vin);
                        }

                        vin.setCode(carDTO.vin().code());
                    }

                    carRepository.save(car);
                    return car;
    }
    public Car saveCarWithVin(CarDTO carDTO) {
        Car car = new Car();
        car.setMake(carDTO.make());
        car.setModel(carDTO.model());
        car.setEngineType(carDTO.engineType());
        car.setYear(carDTO.year());

        if(carDTO.vin() != null) {
            VIN vin = new VIN();
            vin.setCode(carDTO.vin().code());
            car.setVin(vin);
        }

        carRepository.save(car);
        return car;
    }


}
