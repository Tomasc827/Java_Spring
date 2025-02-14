package lt.techin.cat_cafe_shop.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequestDTO (@NotNull(message = "Name cannot be null")
                              @Size(min = 4,max = 100, message = "Name must be from 4 to 100 characters")
                              @Pattern(
                                      regexp = "^[a-zA-Z][a-zA-Z0-9._-]{1,98}[a-zA-Z0-9]$",
                                      message = "Name must start and end with alphanumeric characters and can contain" +
                                              " dots, underscores, and hyphens"
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
                              @NotNull(message = "Password cannot be null")
                              @Pattern(
        regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\W_])[\\S]{10,128}$",
        message = "Password must contain at least one lowercase letter, one uppercase letter, " +
                "one number, one special character, and be 8-128 characters long"
)
                              String password) {
}
