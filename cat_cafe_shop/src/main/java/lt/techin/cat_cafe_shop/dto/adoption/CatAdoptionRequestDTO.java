package lt.techin.cat_cafe_shop.dto.adoption;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CatAdoptionRequestDTO (@NotNull(message = "Cat name cannot be null")
                                     @Pattern(regexp = "^[A-Z][a-zA-Z-]{2,100}$",message = "Must start with uppercase letter, allows only letters and hyphens, 2 to 100 characters in length")
                                     String catName) {
}
