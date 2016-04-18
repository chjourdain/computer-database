package com.excilys.formation.computerdatabase.model.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import com.excilys.formation.computerdatabase.model.dto.ComputerDTO;

public class ComputerDtoValidator {/* implements Validator {

    @Override
    public boolean supports(Class<?> arg0) {
	return ComputerDTO.class.equals(arg0);
    }

    @Override
    public void validate(Object arg0, Errors errs) {
	ComputerDTO dto = (ComputerDTO) arg0;
	 ValidationUtils.rejectIfEmptyOrWhitespace(errs, "name", "name.required");
	 if (dto.getName() != null || dto.getName().length() <= 3) {
	     errs.reject("name", "Name must have more than 3 characters");
	 }
    }*/
}
