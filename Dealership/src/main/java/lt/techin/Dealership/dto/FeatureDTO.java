package lt.techin.Dealership.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

public record FeatureDTO(@NotBlank(message = "Feature cannot be blank")
                         @Size(min = 2, max = 100, message = "Feature 2 to 100 chars")
                         String carFeature,
                         @NotBlank(message = "Description cannot be blank")
                         @Size(min = 10, max = 500, message = "Description 10 to 500 chars")
                         String description,
                         long id,
                         List<CarDTO> cars) {
}
