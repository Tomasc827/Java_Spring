package lt.techin.Online_Course_Platform.dto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lt.techin.Online_Course_Platform.model.Instructor;
import lt.techin.Online_Course_Platform.validation.NoMultipleWhitespaces;
import org.hibernate.validator.constraints.Range;

public record CourseDTO(@NotNull(message = "Title cannot be null")
                        @Size(min = 3, max = 100, message = "Title must be from 3 to 100 characters")
                        @NoMultipleWhitespaces
                        String title,
                        @NotNull(message = "Description cannot be null")
                        @Size(min = 3, max = 100, message = "Description must be from 3 to 100 characters")
                        @NoMultipleWhitespaces
                        String description,
                        @Range(min = 12, max = 1000, message = "Duration must be from 12 to 1000 hours")
                        int duration,
                        Instructor instructor) {


}
