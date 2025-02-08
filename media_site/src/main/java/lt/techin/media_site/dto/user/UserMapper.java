package lt.techin.media_site.dto.user;

import lt.techin.media_site.model.User;

import java.util.List;

public class UserMapper {


public static UserPostDTO toDTO(User user) {
    return new UserPostDTO(user.getEmail(),
            user.getPassword(),
            user.getImageURL(),
            user.getUsername(),
    user.getDob());
}

public static User toEntity(UserPostDTO dto) {
    User user = new User();
    user.setPassword(dto.password());
    user.setEmail(dto.email());
    user.setImageURL(dto.imageURL());
    user.setUsername(dto.username());
    user.setDob(dto.dob());

    return user;

}

public static List<UserGetDTO>  getToEntity(List<User> users) {
    return users.stream().map(user -> {
        return new UserGetDTO(
        user.getId(),
        user.getUsername(),
        user.getEmail(),
        user.getDob()
);
    }).toList();
}

public static UserResponseDTO toResponseDTO(User user) {
    return new UserResponseDTO(user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getDob());

}

}
