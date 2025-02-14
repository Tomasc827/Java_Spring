package lt.techin.cat_cafe_shop.dto.user;

import lt.techin.cat_cafe_shop.model.Role;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponseDTO(long id, String name, LocalDateTime timeCreated, List<Role> roles) {
}
