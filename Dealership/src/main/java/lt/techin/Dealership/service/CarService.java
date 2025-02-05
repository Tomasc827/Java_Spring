package lt.techin.Dealership.service;


import lt.techin.Dealership.exceptions.CarNotFoundException;
import lt.techin.Dealership.dto.CarDTO;
import lt.techin.Dealership.exceptions.FeatureNotFoundException;
import lt.techin.Dealership.model.Car;
import lt.techin.Dealership.model.Feature;
import lt.techin.Dealership.model.VIN;
import lt.techin.Dealership.repository.CarRepository;
import lt.techin.Dealership.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

  private CarRepository carRepository;
  private FeatureRepository featureRepository;

  @Autowired
  public CarService(CarRepository carRepository, FeatureRepository featureRepository) {
    this.carRepository = carRepository;
    this.featureRepository = featureRepository;
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
    Car existingCar = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id));
    existingCar.setYear(carDTO.year());
    existingCar.setMake(carDTO.make());
    existingCar.setModel(carDTO.model());
    existingCar.setEngineType(carDTO.engineType());

    if (carDTO.features() != null && !carDTO.features().isEmpty()) {
      List<Feature> features = carDTO.features().stream()
              .map(featureDTO -> {
                Feature feature = featureRepository.findById(featureDTO.id())
                        .orElseThrow(() -> new FeatureNotFoundException(featureDTO.id()));
                feature.getCars().add(existingCar);
                return feature;
              }).toList();

      existingCar.setFeatures(features);
    }

    if (carDTO.vin() != null) {
      VIN vin = existingCar.getVin();
      if (vin == null) {
        vin = new VIN();
        existingCar.setVin(vin);
      }

      vin.setCode(carDTO.vin().code());
    }


    return carRepository.save(existingCar);
  }

  public Car saveCarWithVin(CarDTO carDTO) {
    Car car = new Car();
    car.setMake(carDTO.make());
    car.setModel(carDTO.model());
    car.setEngineType(carDTO.engineType());
    car.setYear(carDTO.year());

    if (carDTO.features() != null) {
      List<Feature> features = carDTO.features().stream()
              .map(featureDTO -> {
                return featureRepository.findById(featureDTO.id())
                        .orElseThrow(() -> new FeatureNotFoundException(featureDTO.id()));
              }).toList();

      car.setFeatures(features);
    }

    if (carDTO.vin() != null) {
      VIN vin = new VIN();
      vin.setCode(carDTO.vin().code());
      car.setVin(vin);
    }

    carRepository.save(car);
    return car;
  }


}
