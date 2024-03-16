package com.github.projetoifsc.estagios.app.utils.validation;

import com.github.projetoifsc.estagios.app.exception.InvalidException;

public class RequestParamValidation {
	
	public void validateUserId (Long userId) { // TODO - essa é a validação do parâmetro para autenticação
		if ( userId == null || userId < 1 )
			throw new InvalidException("User Id must be greater than 0");
	}
	

	public void validateVagaId (Long vagaId) { // TODO - essa é a validação do parâmetro para autenticação
		if ( vagaId < 1 )
			throw new InvalidException("Vaga Id must be greater than 0");
	}

	
}
