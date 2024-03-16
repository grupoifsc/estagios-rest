package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.dto.shared.Contato;

public class ContatoMock {
	
	public static Contato getOne() {
		Contato resource = new Contato();
		resource.setEmail ("nome@email.com");
		resource.setPhone("(XX) XXXX-XXXX");
		return resource;
	}


}
