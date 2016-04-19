package com.excilys.formation.computerdatabase.model.validator.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.excilys.formation.computerdatabase.model.validator.DateConstraintValidator;

@Documented
@Constraint(validatedBy = DateConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateValid {
    String message() default "Invalid Date Format, please enter a 'YYYY-MM-DD' format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}