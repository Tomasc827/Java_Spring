package lt.techin.cat_cafe_shop.dto.reservation;

import lt.techin.cat_cafe_shop.model.Reservation;

public class ReservationMapper {


    public static Reservation reservationToEntity(ReservationRequestDTO dto) {
        Reservation reservation = new Reservation();
        reservation.setDateOfReservation(dto.dateOfReservation());
        reservation.setNumGuests(dto.numGuests());
        reservation.setTimeSlot(dto.timeSlot());

        return reservation;
    }
}
