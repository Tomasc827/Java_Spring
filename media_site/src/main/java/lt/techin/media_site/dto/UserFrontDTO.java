package lt.techin.media_site.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.media_site.validation.NoMultipleWhitespaces;

public record UserFrontDTO(@NotNull(message = "Username cannot be null")
                           @Size(min = 3,max = 100, message = "Username must be from 3 to 100 characters")
                           @NoMultipleWhitespaces
                           @Pattern(
                                   regexp = "^[a-zA-Z0-9][a-zA-Z0-9._-]{1,98}[a-zA-Z0-9]$",
                                   message = "Username must start and end with alphanumeric characters and can contain dots, underscores, and hyphens"
                           )
                           String username,
                           @Size(min = 15, max = 2000)
                           @NoMultipleWhitespaces
                           @Pattern(    regexp = "^https?:\\/\\/(?:[a-zA-Z0-9\\-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})+$",
                                   message = "Must be a valid HTTP/HTTPS URL")
                           String imageURL) {
}
