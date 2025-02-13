package lt.techin.car_rental.dto.carDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.car_rental.model.CarStatus;
import org.hibernate.validator.constraints.Range;

public record CarRequestDTO (@NotNull(message = "Brand cannot be null")
                             @Size(min = 2,max = 100, message = "Brand must be from 3 to 100 characters")
                             @Pattern(
                                     regexp = "^[a-zA-Z0-9][a-zA-Z0-9]{1,98}[a-zA-Z0-9]$",
                                     message = "Brand must start and end with alphanumeric characters"
                             )
                             String brand,
                             @NotNull(message = "Model cannot be null")
                             @Size(min = 2,max = 100, message = "Model must be from 3 to 100 characters")
                             @Pattern(
                                     regexp = "^[a-zA-Z0-9][a-zA-Z0-9._-]{1,98}[a-zA-Z0-9]$",
                                     message = "Model must start and end with alphanumeric characters and can contain dots, underscores, and hyphens"
                             )
                             String model,
                             @Range(min = 1920, max = 2026, message = "The starting year is 1920 and ending year is 2026")
                             int year
                        ){
}
