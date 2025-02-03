package lt.techin.New_Movie_Studio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lt.techin.New_Movie_Studio.model.Screening;

import java.util.List;

public record MovieDTO(@NotBlank(message = "Title cannot be blank")
                       @Size(min = 2, max = 120, message = "Title from 2 to 120 chars")
                       String title,
                       @NotBlank(message = "Director cannot be blank")
                       @Size(min = 2, max = 120, message = "Director from 2 to 120 chars")
                       String director,
                       List<ScreeningsDTO> screenings,
                       List<ActorDTO> actors) {
}
