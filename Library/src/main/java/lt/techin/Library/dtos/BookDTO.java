package lt.techin.Library.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lt.techin.Library.models.Condition;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

public record BookDTO(@NotBlank(message = "Title is required")
                      String title,
                      @NotBlank(message = "Author is required")
                      String author,
                      @Range(min = 1400, max = 2026, message = "Year can be from 1400 to 2026")
                      int publishingYear,
                      @NotNull(message = "Price is required")
                      @DecimalMin(value = "0.01", message = "Price cannot be negative")
                      @DecimalMax(value = "999.99", message = "Book price is limited to 1000 not inclusive")
                      BigDecimal price,
                      @NotNull(message = "Condition must be specified")
                      Condition condition,
                      boolean isRented) {
}
