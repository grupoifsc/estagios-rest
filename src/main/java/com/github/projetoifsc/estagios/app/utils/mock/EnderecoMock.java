package com.github.projetoifsc.estagios.app.utils.mock;
//import static com.github.juhachmann.estagios.data.mock.MockDatabasePerfil.enderecoDTOMockCollection;

import com.github.projetoifsc.estagios.app.model.shared.AddressView;
import org.springframework.stereotype.Service;

@Service
public class EnderecoMock {

	public static AddressView getOne() {
		AddressView resource = new AddressView();
		resource.setRua("Rua xxxx");
		resource.setCidade("Florianópolis");
		resource.setEstado("SC");
		resource.setPais("Brasil");
		return resource;
	}
	
}
