package lt.techin.New_Movie_Studio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ActorDTO(@NotBlank(message = "Name cannot be null")
                       @Size(min = 2, max = 120, message = "name must be from 2 to 120 chars")
                       String name,
                       LocalDate dob) {


}
