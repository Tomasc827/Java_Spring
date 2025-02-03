package lt.techin.Dealership.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lt.techin.Dealership.model.EngineType;
import org.hibernate.validator.constraints.Range;

import java.util.List;

public record CarDTO(@NotBlank(message = "Car make cannot be empty")
                     @Size(min = 2,max = 100,message = "Make from 2 to 100 chars")
                     String make,
                     @NotBlank(message = "Model cannot be empty")
                     @Size(min = 2,max = 100,message = "Model from 2 to 100 chars")
                     String model,
                     @NotNull(message = "Engine type cannot be null")
                     EngineType engineType,
                     @NotNull(message = "Year cannot be null")
                     @Range(min = 1940,max = 2025, message = "Year must be from 1940 to 2025")
                     int year,
                     VINDTO vin,
                     List<FeatureDTO> features,
                     long id) {
}
