package lt.techin.Dealership.service;


import lt.techin.Dealership.CarNotFoundException;
import lt.techin.Dealership.dto.FeatureDTO;
import lt.techin.Dealership.model.Car;
import lt.techin.Dealership.model.Feature;
import lt.techin.Dealership.repository.CarRepository;
import lt.techin.Dealership.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    private FeatureRepository featureRepository;

    private CarRepository carRepository;

    @Autowired
    public FeatureService(FeatureRepository featureRepository,CarRepository carRepository) {
        this.carRepository = carRepository;
        this.featureRepository = featureRepository;
    }

    public Feature postFeatureService(FeatureDTO featureDTO) {
        Feature feature = new Feature();
        feature.setCarFeature(featureDTO.carFeature());
        feature.setDescription(featureDTO.description());
        List<Car> cars = featureDTO.cars().stream()
                .map(carDTO -> {
                    return carRepository.findById(carDTO.id())
                            .orElseThrow(() -> new CarNotFoundException(carDTO.id()));
                }).toList();
        feature.setCars(cars);
        return feature;

    }


    public List<Feature> findAllFeatures() {
        return featureRepository.findAll();
    }
}
