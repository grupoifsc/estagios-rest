package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.model.shared.ContactView;

public class ContactMock {
	
	public static ContactView getOne() {
		ContactView resource = new ContactView();
		resource.setEmail ("nome@email.com");
		resource.setTelefone("(XX) XXXX-XXXX");
		return resource;
	}


}
