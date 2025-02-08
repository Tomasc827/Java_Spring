package lt.techin.media_site.dto.user;

import java.time.LocalDate;

public record UserGetDTO(long id, String username, String email, LocalDate dob) {
}
