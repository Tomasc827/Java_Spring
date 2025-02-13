package lt.techin.car_rental.controller;


import jakarta.validation.Valid;
import lt.techin.car_rental.dto.carDTO.*;
import lt.techin.car_rental.model.Car;
import lt.techin.car_rental.service.CarService;
import lt.techin.car_rental.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<CarResponseDTO> addCar(@Valid @RequestBody CarRequestDTO dto) {
        Car car = carService.addCar(dto);
        CarResponseDTO dtoResponse = CarMapper.carToResponseDTO(car);
        URI location = WebUtils.createLocation("/{id}",car.getId());
        return ResponseEntity.created(location).body(dtoResponse);
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<?> removeCar(@PathVariable long id) {
        carService.removeCar(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<CarResponseDTO> updateCar(@Valid @RequestBody CarRequestDTO dto, @PathVariable long id) {
        Car updatedCar = carService.updateCar(dto, id);
        return ResponseEntity.ok(CarMapper.carToResponseDTO(updatedCar));
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<?> changeRentalStatus(@PathVariable long id) {
        String message = carService.changeRentalStatus(id);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<Car>> findAllCarsAdmins() {
        return ResponseEntity.ok(carService.getAllCarsAdmin());
    }
    @GetMapping("/available")
    public ResponseEntity<List<Car>> findAllCarsPublic() {
        return ResponseEntity.ok(carService.getAllCarsPublic());
    }

}
