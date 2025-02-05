package lt.techin.media_site.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoMultipleWhitespacesValidator implements ConstraintValidator<NoMultipleWhitespaces,String> {

    private int maxConsecutiveSpaces;

    @Override
    public void initialize(NoMultipleWhitespaces constraintAnnotation) {
        this.maxConsecutiveSpaces = constraintAnnotation.maxConsecutiveSpaces();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        String regex = String.format(".*\\s{%d,}.*", maxConsecutiveSpaces + 1);
        return !value.matches(regex);
    }

}
