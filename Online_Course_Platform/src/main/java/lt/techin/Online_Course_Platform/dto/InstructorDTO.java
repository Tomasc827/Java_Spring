package lt.techin.Online_Course_Platform.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.Online_Course_Platform.validation.NoMultipleWhitespaces;

import java.util.List;

public record InstructorDTO(@NotNull(message = "Name cannot be null")
                            @Size(min = 3, max = 100, message = "Name must be from 3 to 100 characters")
                            @NoMultipleWhitespaces
                            String name,

                            @NotNull(message = "Email cannot be null")
                            @Size(min = 5, max = 120, message = "Email must be from 5 to 120 characters")
                            @NoMultipleWhitespaces
                            @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email format")
                            String email,

                            @NotNull(message = "Expertise cannot be null")
                            @Size(min = 3, max = 50, message = "Expertise must be from 3 to 50 characters")
                            @NoMultipleWhitespaces
                            String expertise,

                            @NotNull(message = "address cannot be null")
                            @Size(min = 5, max = 200, message = "Address must be from 5 to 200")
                            @NoMultipleWhitespaces
                            String address,
                            List<CourseDTO> courses) {

}
