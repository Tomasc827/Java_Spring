package lt.techin.Library.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.processing.Pattern;

public record MemberDTO(
        @Size(min = 3, max = 100, message = "Name has to be from 3 to 100 characters")
        @NotBlank(message = "Name cannot be empty") String name,
        @NotBlank(message = "Password cannot be empty")
        @Size(min = 8, max = 100, message = "Password must be from 8 to 500 characters long")
        String password,
        @Email(message = "Email is invalid")
        @NotBlank(message = "Email Cannot Be empty")
        String email
) {


}
