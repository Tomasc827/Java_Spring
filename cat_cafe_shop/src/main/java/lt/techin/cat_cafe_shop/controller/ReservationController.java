package lt.techin.cat_cafe_shop.controller;


import jakarta.validation.Valid;
import lt.techin.cat_cafe_shop.dto.reservation.ReservationRequestDTO;
import lt.techin.cat_cafe_shop.dto.reservation.ReservationResponseDTO;
import lt.techin.cat_cafe_shop.model.Reservation;
import lt.techin.cat_cafe_shop.service.ReservationService;
import lt.techin.cat_cafe_shop.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO dto) {
        Reservation reservation = reservationService.createReservation(dto);
        ReservationResponseDTO responseDTO = new ReservationResponseDTO(reservation.getId(),
                reservation.getUser(),
                reservation.getDateOfReservation(),
                reservation.getTimeSlot(),
                reservation.getNumGuests());
        URI location = WebUtils.createLocation("/{id}",reservation.getId());
        return ResponseEntity.created(location).body(responseDTO);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ROLE_USER')")
    public ResponseEntity<List<Reservation>> findAllUserReservations() {
        return ResponseEntity.ok(reservationService.findUserReservations());
    }
    @DeleteMapping("/{reservationId}")
    @PreAuthorize("hasAuthority('SCOPE_ROLE_ADMIN')")
    public ResponseEntity<?> cancelReservation(@PathVariable long reservationId) {
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>(
                Map.of("message", "Reservation successfully cancelled"),
                HttpStatus.OK
        );
    }

}
