package lt.techin.car_rental.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class isOfAgeValidator implements ConstraintValidator<IsOfAge,LocalDate> {


    @Override
    public void initialize(IsOfAge constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(LocalDate birthdate, ConstraintValidatorContext context) {
        if (birthdate == null) {
            return true;
        }
        LocalDate now = LocalDate.now();
        Period age = Period.between(birthdate, now);

        return age.getYears() >= 18;
    }
}
