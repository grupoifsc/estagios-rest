package com.github.projetoifsc.estagios.app.utils.validation;

/**
 * Interface to add validation behaviour
 * 
 */
public interface Validatable {
	
	/**
	 * Validates the resource using Validation Helper
	 *
	 */
	default void validate() {
		ValidationHelper.validate(this);
	};

}
