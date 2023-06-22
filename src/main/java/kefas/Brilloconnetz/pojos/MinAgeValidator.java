package kefas.Brilloconnetz.pojos;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import kefas.Brilloconnetz.anotations.MinAge;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class MinAgeValidator implements ConstraintValidator<MinAge, LocalDate> {
    private int minAge;

    @Override
    public void initialize(MinAge constraintAnnotation) {
        this.minAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate dateOfBirth, ConstraintValidatorContext context) {
        if (dateOfBirth == null) {
            return true;
        }

        LocalDate currentDate = LocalDate.now();
        long age = ChronoUnit.YEARS.between(dateOfBirth, currentDate);
        return age >= minAge;
    }
}
