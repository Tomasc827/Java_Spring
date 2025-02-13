package lt.techin.car_rental.dto.rentalDTO;

import lt.techin.car_rental.model.Rental;

public class RentalMapper {



    public static Rental toEntity(RentalRequestDTO dto) {
        Rental rental = new Rental();
        rental.setRentalEnd(dto.rentalEnd());
        rental.setRentalStart(dto.rentalStart());
        rental.setPrice(dto.price());
        return rental;
    }
}
