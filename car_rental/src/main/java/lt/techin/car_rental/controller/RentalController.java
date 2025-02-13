package lt.techin.car_rental.controller;


import jakarta.validation.Valid;
import lt.techin.car_rental.dto.rentalDTO.RentalRequestDTO;
import lt.techin.car_rental.dto.rentalDTO.RentalResponseDTO;
import lt.techin.car_rental.dto.rentalDTO.RentalReturnDTO;
import lt.techin.car_rental.model.Rental;
import lt.techin.car_rental.service.RentalService;
import lt.techin.car_rental.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {

    private final UserService userService;
    private final RentalService rentalService;

    @Autowired
    public RentalController(UserService userService, RentalService rentalService) {
        this.userService = userService;
        this.rentalService = rentalService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<RentalResponseDTO> createRental(@Valid @RequestBody RentalRequestDTO dto) {
        Rental rental = userService.addRental(dto);
        RentalResponseDTO responseDTO = new RentalResponseDTO(rental.getCar().getId(),
                rental.getUser().getId()
                ,rental.getId(),
                rental.getRentalStart(),
                rental.getRentalEnd(),
                rental.getPrice());

        return ResponseEntity.ok(responseDTO);
    }

    @PostMapping("/return/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<RentalReturnDTO> closeRental(@PathVariable long id) {
        userService.closeRental(id);
        Rental rental = rentalService.findRentalById(id);
        RentalReturnDTO dto = new RentalReturnDTO(rental.getId(),
                rental.getUser().getId(),
                rental.getCar().getId(),
                rental.getPrice());
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/history")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<List<Rental>> getRentalHistory() {
        return ResponseEntity.ok(rentalService.findAllRentals());
    }

    @GetMapping("/my")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<List<Rental>> getAllUserRentals() {
        return ResponseEntity.ok(rentalService.findAllUserRentals());
    }
}
