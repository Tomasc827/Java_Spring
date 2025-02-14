package lt.techin.cat_cafe_shop.dto.user;

import lt.techin.cat_cafe_shop.model.User;

public class UserMapper {

    public static User userToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        return user;
    }

}
