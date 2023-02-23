package br.com.domain.anotation.validator;

import java.util.List; 

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.domain.anotation.NotEmptyList;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList,List> {

	@Override
	public boolean isValid(List value, ConstraintValidatorContext context) {
		
		return !value.isEmpty() && value!=null;
	}
	
	@Override
	public void initialize(NotEmptyList constraintAnnotation) {
		
	}

}
