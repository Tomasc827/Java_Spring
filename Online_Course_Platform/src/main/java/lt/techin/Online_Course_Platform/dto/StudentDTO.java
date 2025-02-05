package lt.techin.Online_Course_Platform.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lt.techin.Online_Course_Platform.model.Course;
import lt.techin.Online_Course_Platform.validation.NoMultipleWhitespaces;
import lt.techin.Online_Course_Platform.validation.ValidAge;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.List;

public record StudentDTO(@NotNull(message = "Name cannot be null")
                         @Size(min = 3, max = 100, message = "Name must be from 3 to 100 characters")
                         @NoMultipleWhitespaces
                         String name,
                         @NotNull(message = "Email cannot be null")
                         @Size(min = 3, max = 120, message = "Email must be from 3 to 100 characters")
                         @NoMultipleWhitespaces
                         @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "invalid email format")
                         String email,
                         @NotNull(message = "Date of birth cannot be null")
                         @Past(message = "Date of birth must be in the past")
                         @ValidAge
                         LocalDate dob,
                         List<Course> courses) {
}
