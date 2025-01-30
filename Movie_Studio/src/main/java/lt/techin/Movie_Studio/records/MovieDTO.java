package lt.techin.Movie_Studio.records;

import jakarta.validation.constraints.NotBlank;


public record MovieDTO(
        @NotBlank(message = "Title cannot be empty,null or only haves whitespaces")
        String title,
        @NotBlank(message = "Director cannot be empty,null or only haves whitespaces")
        String director) {
}
