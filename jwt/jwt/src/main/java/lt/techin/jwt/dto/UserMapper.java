package lt.techin.jwt.dto;

import lt.techin.jwt.model.User;

public class UserMapper {

    public static User userToEntity(UserRequestDTO dto) {
        User user = new User();
        user.setEmail(dto.email());
        user.setPassword(dto.password());
        user.setUsernamo(dto.usernamo());
        return user;
    }

    public static UserGetDTO userToDTO(User user) {
        return new UserGetDTO(user.getId(), user.getUsernamo(), user.getEmail());
    }
}
