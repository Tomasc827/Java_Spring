package lt.techin.media_site.dto.user;



import java.time.LocalDate;


public record UserResponseDTO(long id, String email, String username, LocalDate dob) {
}
