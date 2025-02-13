package lt.techin.car_rental.dto.userDTO;

import lt.techin.car_rental.model.User;

public class UserMapper {


    public static User userToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setAge(dto.age());
        user.setPassword(dto.password());
        user.setHasLicense(dto.hasLicense());
        return user;
    }
}
