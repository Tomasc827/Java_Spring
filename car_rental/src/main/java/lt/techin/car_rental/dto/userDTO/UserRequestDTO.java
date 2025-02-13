package lt.techin.car_rental.dto.userDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.car_rental.validation.IsOfAge;

import java.time.LocalDate;

public record UserRequestDTO(@NotNull(message = "Username cannot be null")
                             @Size(min = 3,max = 100, message = "Username must be from 3 to 100 characters")
                             @Pattern(
                                     regexp = "^[a-zA-Z0-9][a-zA-Z0-9._-]{1,98}[a-zA-Z0-9]$",
                                     message = "Username must start and end with alphanumeric characters and can contain dots, underscores, and hyphens"
                             )
                             String name,
                             @NotNull(message = "Email cannot be null")
                             @Size(max = 254, message = "Email cannot exceed 254 characters")
                             @Pattern(
                                     regexp = "^[a-zA-Z0-9][a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]*(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                                             + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$",
                                     message = "Must be a valid email address"
                             )
                             String email,
                             @Pattern(
                                     regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[\\S]{8,128}$",
                                     message = "Password must contain at least one lowercase letter, one uppercase letter, " +
                                             "one number, one special character, and be 8-128 characters long"
                             )
                             String password,
                             @NotNull(message = "Age cannot be null")
                             @IsOfAge
                             LocalDate age,
                             @NotNull(message = "Must pick and option for license ownership")
                             boolean hasLicense) {
}
