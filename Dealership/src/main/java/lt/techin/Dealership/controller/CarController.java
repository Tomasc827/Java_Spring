package lt.techin.Dealership.controller;


import jakarta.validation.Valid;
import lt.techin.Dealership.CarNotFoundException;
import lt.techin.Dealership.WebUtils;
import lt.techin.Dealership.dto.CarDTO;
import lt.techin.Dealership.model.Car;
import lt.techin.Dealership.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }


    @PostMapping
    public ResponseEntity<Car> addCar(@Valid @RequestBody CarDTO carDTO) {
    Car carVin = carService.saveCarWithVin(carDTO);

        URI location = WebUtils.createLocation("/{id}",carVin.getId());
        return ResponseEntity.created(location).body(carVin);
    }

    @GetMapping
    public ResponseEntity<List<Car>> findAllCars() {
        List<Car> cars = carService.findAllCars();
        return ResponseEntity.ok().body(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> findCarById(@PathVariable long id) {
        return carService.findCarById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new CarNotFoundException(id));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCarById(@PathVariable long id) {
        return carService.findCarById(id)
                .map(car -> {
                    carService.deleteCarById(id);
                    return ResponseEntity.noContent().build();
                }).orElseThrow(() -> new CarNotFoundException(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> replaceCar(@Valid @RequestBody CarDTO carDTO, @PathVariable long id) {
    Car updatedCar = carService.updatedCarWithVIN(id,carDTO);

                    URI location = WebUtils.createLocation("/{id}", updatedCar.getId());
                    return ResponseEntity.created(location).body(updatedCar);
    }

}
