package lt.techin.media_site.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserLoginDTO(@NotNull
                           @Size(max = 254, message = "Email cannot exceed 254 characters")
                           @Pattern(
                                   regexp = "^[a-zA-Z0-9][a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]*(?:\\.[a-zA-Z0-9_!#$%&'*+/=?`{|}~^-]+)*@"
                                           + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,}$",
                                   message = "Must be a valid email address"
                           )
                            String email,
                           @NotNull(message = "Username cannot be null")
                           @Pattern(
                                   regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[\\S]{8,128}$",
                                   message = "Password must contain at least one lowercase letter, one uppercase letter, " +
                                           "one number, one special character, and be 8-128 characters long"
                           )
                           String password) {
}
