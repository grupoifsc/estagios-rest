package com.github.projetoifsc.estagios.app.utils.validation;

import java.util.Set;

import com.github.projetoifsc.estagios.app.exception.InvalidException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

/**
 * Helper for Jakarta {@link Validation} using Jakarta Default {@link ValidatorFactory}
 * @param <T> resource class
 */
public class ValidationHelper {

	private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	private static final Validator validator = factory.getValidator();
	
	/**
	 * Helper to use Jakarta {@link Validator} with Jakarta Default {@link ValidatorFactory}
	 * 
	 * @param resource to be validated
	 * @throws InvalidException
	 */
	public static <T> void validate(T resource) {
		
		Set<ConstraintViolation<T>> violations = validator.validate(resource) ;
						
		if(violations.isEmpty()) {
			return;
		}
		
//		List<String> errors = new ArrayList<>();
			
		String message = "";
		
		for (ConstraintViolation<T> violation : violations) {
			message = message.concat(violation.toString());
		}
		
//		violations.forEach(violation -> 			
//			errors.add(
//					"{ "
//					+ "\"field\" : \"" + violation.getPropertyPath() + "\", "
//					+ "\"error\" : \"" + violation.getMessage() + "\", "
//					+ "\"given\" : \"" + violation.getInvalidValue() + "\" "
//					+ "}" 
//			));
				
//		System.out.println(errors);
		
		throw new InvalidException(message);
		
	}
	
}
