package lt.techin.car_rental.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Constraint(validatedBy = isOfAgeValidator.class)
@Target({ElementType.METHOD,ElementType.PARAMETER,ElementType.ANNOTATION_TYPE,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IsOfAge {
    String message() default "User must be 18 years of age to rent a car";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
