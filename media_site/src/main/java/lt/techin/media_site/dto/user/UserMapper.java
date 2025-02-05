package lt.techin.media_site.dto.user;

import lt.techin.media_site.model.User;

public class UserMapper {

public static UserPostDTO toDTO(User user) {
    return new UserPostDTO(user.getEmail(),
            user.getPassword(),
            user.getImageURL(),
            user.getUsername());
}

public static User toEntity(UserPostDTO dto) {
    User user = new User();
    user.setPassword(dto.password());
    user.setEmail(dto.email());
    user.setImageURL(dto.imageURL());
    user.setUsername(dto.username());

    return user;

}

public static UserResponseDTO toResponseDTO(User user) {
    return new UserResponseDTO(user.getId(),
            user.getUsername(),
            user.getEmail());

}

}
