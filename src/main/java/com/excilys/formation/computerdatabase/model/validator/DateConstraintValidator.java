package com.excilys.formation.computerdatabase.model.validator;

import java.time.LocalDate;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.excilys.formation.computerdatabase.model.validator.annotations.DateValid;

public class DateConstraintValidator implements ConstraintValidator<DateValid, String> {

    @Override
    public void initialize(DateValid arg0) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext arg1) {
	String dateRegexFr = "^[0-9]{4}-[0-1][0-9]-[0-3][0-9]$|^$";

	if (date == null || date.isEmpty()) {
	    return true;
	}
	if (date.matches(dateRegexFr)) {
	    try {
		LocalDate.parse(date);
	    } catch (Exception e) {
		return false;
	    }
	    return true;
	} else {
	    return false;
	}
    }
}
