package lt.techin.media_site.dto.user;

import java.time.LocalDate;

public record UserFrontDTO(
        long id,
        String username,
        String imageURL,
        LocalDate dob){
}
