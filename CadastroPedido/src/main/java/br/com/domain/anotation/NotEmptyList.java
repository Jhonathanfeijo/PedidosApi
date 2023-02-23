package br.com.domain.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.domain.anotation.validator.NotEmptyListValidator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy=NotEmptyListValidator.class)
public @interface NotEmptyList {
	
	String message() default "Lista não pode estar vazia";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

}
