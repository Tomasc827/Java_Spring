package lt.techin.cat_cafe_shop.service;


import lt.techin.cat_cafe_shop.dto.reservation.ReservationMapper;
import lt.techin.cat_cafe_shop.dto.reservation.ReservationRequestDTO;
import lt.techin.cat_cafe_shop.model.Reservation;
import lt.techin.cat_cafe_shop.model.User;
import lt.techin.cat_cafe_shop.repository.ReservationRepository;
import lt.techin.cat_cafe_shop.repository.UserRepository;
import lt.techin.cat_cafe_shop.validation.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    public Reservation createReservation(ReservationRequestDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("User not found"));
        Reservation reservation = ReservationMapper.reservationToEntity(dto);
        user.getReservations().add(reservation);
        reservation.setUser(user);
        reservationRepository.save(reservation);
        return reservation;
    }
    public List<Reservation> findUserReservations() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userRepository.findByEmail(authentication.getName()).orElseThrow(() -> new NotFoundException("User not found"));
        return reservationRepository.findAllByUser(user);
    }

    public void deleteReservation(long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(() -> new NotFoundException(
                "Reservation with id '" + id + "' was not found"));
        reservationRepository.delete(reservation);
    }

}
