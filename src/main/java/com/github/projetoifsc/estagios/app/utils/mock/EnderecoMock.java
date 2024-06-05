package com.github.projetoifsc.estagios.app.utils.mock;
//import static com.github.juhachmann.estagios.data.mock.MockDatabasePerfil.enderecoDTOMockCollection;

import com.github.projetoifsc.estagios.app.model.shared.AddressModel;
import org.springframework.stereotype.Service;

@Service
public class EnderecoMock {

	public static AddressModel getOne() {
		AddressModel resource = new AddressModel();
		resource.setRua("Rua xxxx");
		resource.setCidade("Florianópolis");
		resource.setEstado("SC");
		resource.setPais("Brasil");
		return resource;
	}
	
}
