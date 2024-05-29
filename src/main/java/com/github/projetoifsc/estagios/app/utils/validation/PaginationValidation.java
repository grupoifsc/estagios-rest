package com.github.projetoifsc.estagios.app.utils.validation;

import com.github.projetoifsc.estagios.app.exception.InvalidException;

public class PaginationValidation {


	public static final String DEFAULT_PAGE_VALUE = "1"; // OFFSET
	public static final String DEFAULT_LIMIT_VALUE = "10";
	public static final int MAX_LIMIT_VALUE = 30;
	
	
	private void validateMaxLimitPageableValue (Integer givenValue) {
		Integer maxValue = MAX_LIMIT_VALUE;
		if (givenValue > maxValue ) {
			var message = "Page parameter must be smaller than %d".formatted(maxValue - 1);
			throw new InvalidException(message);
		}
	}

	public void validateLimitAndOffset (Integer limit, Integer offset) {
		// TODO - essa é a validação do parâmetro para autenticação
		if ( limit < 1 || offset < 1)
			throw new InvalidException("Page and Limit params must not be smaller than 1");
		validateMaxLimitPageableValue(limit);
	}

	
}
