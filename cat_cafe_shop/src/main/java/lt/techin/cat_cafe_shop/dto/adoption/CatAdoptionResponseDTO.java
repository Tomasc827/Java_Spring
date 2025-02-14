package lt.techin.cat_cafe_shop.dto.adoption;

import lt.techin.cat_cafe_shop.model.AdoptionStatus;
import lt.techin.cat_cafe_shop.model.User;

import java.time.LocalDateTime;

public record CatAdoptionResponseDTO(long id, User user, String catName, AdoptionStatus status, LocalDateTime applicationDate) {
}
