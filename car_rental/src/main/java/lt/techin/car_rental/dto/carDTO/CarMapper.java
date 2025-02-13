package lt.techin.car_rental.dto.carDTO;

import lt.techin.car_rental.model.Car;

public class CarMapper {

    public static Car toEntity(CarRequestDTO dto) {
        Car car = new Car();
        car.setYear(dto.year());
        car.setBrand(dto.brand());
        car.setModel(dto.model());
        return car;
    }

    public static CarResponseDTO carToResponseDTO(Car car) {
        return new CarResponseDTO(car.getId(),
                car.getBrand(),
                car.getModel(),
                car.getYear(),
                car.getCarStatus());
    }

}
