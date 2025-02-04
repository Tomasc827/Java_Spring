package lt.techin.Online_Course_Platform.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = NoMultipleWhitespacesValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NoMultipleWhitespaces {

  String message() default "Input must not contain multiple whitespaces";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  int maxConsecutiveSpaces() default 1;
}
