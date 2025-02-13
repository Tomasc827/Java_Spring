package lt.techin.car_rental.dto.rentalDTO;

import java.time.LocalDateTime;

public record RentalResponseDTO (long carId, long userId, long rentalId, LocalDateTime rentalStart, LocalDateTime rentalEnd, double priceTotal){
}
