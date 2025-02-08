package lt.techin.media_site.dto.user;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.media_site.validation.NoMultipleWhitespaces;

import java.time.LocalDate;

public record UserPostDTO(@NotNull(message = "Username cannot be null")
                          @Size(min = 3,max = 100, message = "Username must be from 3 to 100 characters")
                          @NoMultipleWhitespaces
                          @Pattern(
                                  regexp = "^[a-zA-Z0-9][a-zA-Z0-9._-]{1,98}[a-zA-Z0-9]$",
                                  message = "Username must start and end with alphanumeric characters and can contain dots, underscores, and hyphens"
                          )
                          String username,

                          @NotNull
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
                          String password,

                          @Size(min = 15, max = 2000, message = "URL must be from 15 to 2000 characters")
                          @NoMultipleWhitespaces
                          @Pattern(    regexp = "^https?:\\/\\/(?:[a-zA-Z0-9\\-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})+$",
                                  message = "Must be a valid HTTP/HTTPS URL")
                          String imageURL,
                          @NotNull(message = "dob cannot be null")
                          @Pattern(
                                  regexp = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$",
                                  message = "Date must be in YYYY-MM-DD format"
                          )
                          @Past(message = "Date of birth must be in the past")
                          LocalDate dob) {
}

