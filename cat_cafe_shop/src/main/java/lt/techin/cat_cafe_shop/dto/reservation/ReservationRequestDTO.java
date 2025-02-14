package lt.techin.cat_cafe_shop.dto.reservation;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

public record ReservationRequestDTO(@NotNull(message = "Date cannot be null")
                                    @Future(message = "Date must be in the future")
                                    LocalDate dateOfReservation,
                                    @NotNull(message = "Time slot cannot be null")
                                    String timeSlot,
                                    @Range(min = 1,max = 4,message = "Number of guest must be from 1 to 4")
                                    int numGuests) {
}
