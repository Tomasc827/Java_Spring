package lt.techin.cat_cafe_shop.dto.adoption;

import jakarta.validation.constraints.NotNull;
import lt.techin.cat_cafe_shop.model.AdoptionStatus;

public record CatAdoptionPutRequestDTO(@NotNull(message = "Adoption status cannot be null")
                                       AdoptionStatus status) {
}
