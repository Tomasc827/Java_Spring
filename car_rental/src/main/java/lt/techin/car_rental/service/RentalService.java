package lt.techin.car_rental.service;


import lt.techin.car_rental.model.Rental;
import lt.techin.car_rental.model.User;
import lt.techin.car_rental.repository.RentalRepository;
import lt.techin.car_rental.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final UserService userService;

    @Autowired
    public RentalService(RentalRepository rentalRepository, UserService userService) {
        this.rentalRepository = rentalRepository;
        this.userService = userService;
    }



    public Rental findRentalById(long id) {
        return  rentalRepository.findById(id).orElseThrow(() -> new NotFoundException("Rentail with id' " + id + "' was not found"));
    }

    public List<Rental> findAllRentals() {
        return rentalRepository.findAll();
//        return rentalRepository.findAll()
//                .stream()
//                .filter(rental -> rental.getRentalEnd().isBefore(LocalDateTime.now()))
//                .toList();
    }

    public List<Rental> findAllUserRentals() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(authentication.getName());
        return user.getRentals()
                .stream()
                .filter(rental -> rental.getRentalEnd().isAfter(LocalDateTime.now()))
                .toList();

    }

}
