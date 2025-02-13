package lt.techin.car_rental.dto.rentalDTO;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDateTime;

public record RentalRequestDTO(@NotNull(message = "Start date cannot be null")
                            @FutureOrPresent(message = "Start date must be in the future")
                               LocalDateTime rentalStart,
                               @NotNull(message = "End date cannot be null")
                               @FutureOrPresent(message = "End date must be in the future")
                               LocalDateTime rentalEnd,
                               @Range(min = 1,max = 500, message = "The base price range is from 1 to 500, no limos here")
                               double price,
                               @NotNull(message = "car id cannot be null")
                               long carId) {
}
