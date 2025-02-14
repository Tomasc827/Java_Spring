package lt.techin.cat_cafe_shop.dto.reservation;

import lt.techin.cat_cafe_shop.model.User;

import java.time.LocalDate;

public record ReservationResponseDTO(long id, User user, LocalDate dateOfReservation,String timeSlot, int numGuests) {
}
