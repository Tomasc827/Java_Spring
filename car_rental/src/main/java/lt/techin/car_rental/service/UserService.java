package lt.techin.car_rental.service;


import lt.techin.car_rental.dto.rentalDTO.RentalMapper;
import lt.techin.car_rental.dto.rentalDTO.RentalRequestDTO;
import lt.techin.car_rental.dto.userDTO.UserMapper;
import lt.techin.car_rental.dto.userDTO.UserRequestDTO;
import lt.techin.car_rental.model.*;
import lt.techin.car_rental.repository.CarRepository;
import lt.techin.car_rental.repository.RentalRepository;
import lt.techin.car_rental.repository.RoleRepository;
import lt.techin.car_rental.repository.UserRepository;
import lt.techin.car_rental.validation.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final CarRepository carRepository;
    private final RentalRepository rentalRepository;

    @Autowired
    public UserService(UserRepository userRepository,RoleRepository roleRepository, PasswordEncoder passwordEncoder,
                       CarRepository carRepository,RentalRepository rentalRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.carRepository = carRepository;
        this.rentalRepository =rentalRepository;
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User with email '" + email + "' was not found"));
    }

    public User createUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.email())) {
            throw new AlreadyExistsException("Email '" + dto.email() + "is already in use");
        }
        if (!dto.hasLicense()) {
            throw new NoLicenseException("User must own license to register");
        }
        Role roleUser = roleRepository.findByName("ROLE_USER").orElseThrow();
        User user = UserMapper.userToEntity(dto);
        user.getRoles().add(roleUser);
        user.setPassword(passwordEncoder.encode(dto.password()));
        userRepository.save(user);
        return user;
    }

    public Rental addRental(RentalRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user =
                userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("User " +
                        "Not found"));
        Car car =
                carRepository.findById(dto.carId()).orElseThrow(() -> new NotFoundException("Car with id '" + dto.carId() +
                        "does not exist"));
        Rental rental = RentalMapper.toEntity(dto);

        Instant instantStart = dto.rentalStart().atZone(ZoneId.systemDefault()).toInstant();
        Instant instantEnd = dto.rentalEnd().atZone(ZoneId.systemDefault()).toInstant();

        long activeRentals = user.getRentals()
                .stream()
                .filter(rent -> rent.getRentalEnd().isAfter(LocalDateTime.now()))
                .count();

        long rentalStartToMilliSec = instantStart.toEpochMilli();
        long rentalEndToMilliSec = instantEnd.toEpochMilli();
        int days =(int) (rentalEndToMilliSec - rentalStartToMilliSec) / 1000 / 60 / 60 / 24;



        if (days < 1) {
            throw new RentalPeriodException("Rental period must be at least 1 day");
        }

        if (activeRentals >= 2) {
            throw new RentalLimitException("User cannot rent more than two cars at any given time");
        }

        if (car.getCarStatus() != CarStatus.AVAILABLE) {
            throw new CarRentedException("Car with id '" + dto.carId() + "' model: " + car.getModel() + " is currently " +
                    "rented");
        }


        double totalPrice = days * dto.price();
        rental.setPrice(totalPrice);

        car.setCarStatus(CarStatus.RENTED);

        rental.setUser(user);
        rental.setCar(car);

        user.getRentals().add(rental);
        car.getRentals().add(rental);

        rentalRepository.save(rental);
        carRepository.save(car);

        return rental;

    }

    public void closeRental(long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException(
                "User not found"));

        Rental existingRental = rentalRepository.findById(id).orElseThrow(() -> new NotFoundException("Rental with id" +
                " '" + id + "' was not found"));
        Car car = existingRental.getCar();

        if(existingRental.getUser().getId() != user.getId()) {
            throw new OwnershipException("Can only return your own rentals");
        }

        if(LocalDateTime.now().isBefore(existingRental.getRentalEnd())) {
            existingRental.setRentalEnd(LocalDateTime.now());
        }
        if (car.getCarStatus() == CarStatus.AVAILABLE) {
            throw new CarNeverRentedException("Car with id '" + id + "' was not rented");
        }

        Instant instantStart = existingRental.getRentalStart().atZone(ZoneId.systemDefault()).toInstant();
        Instant instantEnd = existingRental.getRentalEnd().atZone(ZoneId.systemDefault()).toInstant();

        long rentalStartToMilliSec = instantStart.toEpochMilli();
        long rentalEndToMilliSec = instantEnd.toEpochMilli();
        int days =(int) (rentalEndToMilliSec - rentalStartToMilliSec) / 1000 / 60 / 60 / 24;

        double totalPrice = days * existingRental.getPrice();

        existingRental.setPrice(totalPrice);

        car.setCarStatus(CarStatus.AVAILABLE);

        carRepository.save(car);
        rentalRepository.save(existingRental);
    }



}
