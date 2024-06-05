package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.model.shared.ContactModel;

public class ContactMock {
	
	public static ContactModel getOne() {
		ContactModel resource = new ContactModel();
		resource.setEmail ("nome@email.com");
		resource.setTelefone("(XX) XXXX-XXXX");
		return resource;
	}


}
