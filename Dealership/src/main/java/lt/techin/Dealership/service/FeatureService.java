package lt.techin.Dealership.service;


import lt.techin.Dealership.exceptions.CarNotFoundException;
import lt.techin.Dealership.dto.FeatureDTO;
import lt.techin.Dealership.exceptions.FeatureNotFoundException;
import lt.techin.Dealership.model.Car;
import lt.techin.Dealership.model.Feature;
import lt.techin.Dealership.repository.CarRepository;
import lt.techin.Dealership.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeatureService {

  private FeatureRepository featureRepository;

  private CarRepository carRepository;

  @Autowired
  public FeatureService(FeatureRepository featureRepository, CarRepository carRepository) {
    this.carRepository = carRepository;
    this.featureRepository = featureRepository;
  }

  public Feature postFeatureService(FeatureDTO featureDTO) {
    Feature feature = new Feature();
    feature.setCarFeature(featureDTO.carFeature());
    feature.setDescription(featureDTO.description());
    List<Car> cars = featureDTO.cars().stream()
            .map(carDTO -> {
              Car car = carRepository.findById(carDTO.id())
                      .orElseThrow(() -> new CarNotFoundException(carDTO.id()));
              car.getFeatures().add(feature);
              return car;
            }).toList();
    feature.setCars(cars);

    featureRepository.save(feature);

    return feature;
  }


  public Feature updateFeature(FeatureDTO featureDTO, long id) {
    Feature existingFeature = featureRepository.findById(id)
            .orElseThrow(() -> new FeatureNotFoundException(id));

    existingFeature.setCarFeature(featureDTO.carFeature());
    existingFeature.setDescription(featureDTO.description());

    if (featureDTO.cars() != null && !featureDTO.cars().isEmpty()) {
      List<Car> cars = featureDTO.cars().stream()
              .map(carDTO -> carRepository.findById(carDTO.id())
                      .orElseThrow(() -> new CarNotFoundException(carDTO.id())))
              .collect(Collectors.toList());

      existingFeature.setCars(cars);

      cars.forEach(car -> car.getFeatures().add(existingFeature));
    }

    return featureRepository.save(existingFeature);
  }


  public List<Feature> findAllFeatures() {
    return featureRepository.findAll();
  }

  public Optional<Feature> findFeatureById(long id) {
    return featureRepository.findById(id);
  }
}
