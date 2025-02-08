package lt.techin.media_site.dto.media;

import java.time.LocalDate;

public record MediaResponseDTO(long id, String title, LocalDate releaseDate) {
}
