package lt.techin.car_rental.dto.carDTO;

import lt.techin.car_rental.model.CarStatus;

public record CarResponseDTO (long id, String brand, String model, int year, CarStatus status){
}
