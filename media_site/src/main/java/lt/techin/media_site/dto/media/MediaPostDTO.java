package lt.techin.media_site.dto.media;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.media_site.model.media_enum.AgeRating;
import lt.techin.media_site.model.media_enum.Type;
import lt.techin.media_site.validation.NoMultipleWhitespaces;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;

public record MediaPostDTO(@NotNull(message = "Title cannot be null")
                           @Size(min = 3,max = 500, message = "Title must be from 3 to 500 characters")
                           @NoMultipleWhitespaces
                           @Pattern(
                                   regexp = "^[a-zA-Z0-9][-a-zA-Z0-9 '.:,!&()]+[a-zA-Z0-9.]$",
                                   message = "Title must start and end with alphanumeric characters and can contain letters, numbers, spaces, and basic punctuation"
                           )
                           String title,
                           @Past(message = "Release date must be in the past")
                           LocalDate releaseDate,
                           @Range(min = 1, max = 2000, message = "Current show episodes must to at least start with 1 up to 2000")
                           int episodeCount,
                           @Range(min = 1, max = 2000, message = "Total show episodes must to at least start with 1 up to 2000")
                           int episodeTotal,
                           @NotNull(message = "Video url cannot be null")
                           @Size(min = 15, max = 2000, message = "Video url must be from 15 to 2000 characters")
                           @NoMultipleWhitespaces
                           @Pattern(    regexp = "^https?:\\/\\/(?:[a-zA-Z0-9\\-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})+$",
                                   message = "Must be a valid HTTP/HTTPS video url")
                           String videoURL,
                           @Size(min = 15, max = 2000, message = "Image url must be from 15 to 2000 characters")
                           @NoMultipleWhitespaces
                           @Pattern(    regexp = "^https?:\\/\\/(?:[a-zA-Z0-9\\-._~!$&'()*+,;=:@\\/]|%[0-9A-F]{2})+$",
                                   message = "Must be a valid HTTP/HTTPS image url")
                           String imageURL,
                           @NotNull(message = "Type cannot be null")
                           Type type,
                           @NotNull(message = "Age rating cannot be null")
                           AgeRating ageRating,
                           @NotNull(message = "Description cannot be null")
                           @Size(min = 3,max = 1000, message = "Description must be from 3 to 1000 characters")
                           @NoMultipleWhitespaces
                           @Pattern(
                                   regexp = "^[a-zA-Z0-9][-a-zA-Z0-9 '.:,!&()]+[a-zA-Z0-9.]$",
                                   message = "Title must start and end with alphanumeric characters and can contain letters, numbers, spaces, and basic punctuation"
                           )
                           String description) {
}
