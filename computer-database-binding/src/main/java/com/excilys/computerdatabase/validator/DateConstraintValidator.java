package com.excilys.computerdatabase.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.validator.GenericValidator;
import com.excilys.computerdatabase.validator.annotations.DateValid;

public class DateConstraintValidator implements ConstraintValidator<DateValid, String> {

    @Override
    public void initialize(DateValid arg0) {
    }

    @Override
    public boolean isValid(String date, ConstraintValidatorContext arg1) {
	if (date == null || date.isEmpty() || GenericValidator.isDate(date, "MM-dd-yyyy", true)
		|| GenericValidator.isDate(date, "dd-MM-yyyy", true)) {
	    return true;
	}
	return false;
    }
}
