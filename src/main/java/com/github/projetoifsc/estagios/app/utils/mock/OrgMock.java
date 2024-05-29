package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfileResponse;

import java.util.ArrayList;
import java.util.List;

public class OrgMock {

	public static OrgPrivateProfileResponse getOne() {
	//	return getList().get(0);
		return null;
	}

	public static List<OrgPrivateProfileResponse> getList() {
		var list = new ArrayList<OrgPrivateProfileResponse>(List.of(
//			new OrgPrivateProfileBasicView("1", "NoBanks", false, "empresa nobanks", ContatoMock.getOne(), "nobanks", "94169927000101", LocalizacaoMock.getOne(), ContatoMock.getOne(), LocalDateTime.now(), LocalDateTime.now()),
//			new OrgPrivateProfileBasicView("2", "Giggle", false, "empresa giggle", ContatoMock.getOne(), "giggle", "57576707000168", LocalizacaoMock.getOne(), null, LocalDateTime.now(), LocalDateTime.now())
		));
		list.addAll(getSchools());
		return list;
	}

	public static OrgPrivateProfileResponse getInvalid() {
		return new OrgPrivateProfileResponse();
	}

	public static List<OrgPrivateProfileResponse> getSchools() {
		return List.of(
//			new OrgPrivateProfileBasicView("3", "IFXC", true, "instituto federal", ContatoMock.getOne(),"ifxc", "16417198000143", LocalizacaoMock.getOne(), null, LocalDateTime.now(), LocalDateTime.now()),
//			new OrgPrivateProfileBasicView("4", "UFXC", true, "universidade federal", ContatoMock.getOne(),"ufxc", "71962402000115", LocalizacaoMock.getOne(), ContatoMock.getOne(), LocalDateTime.now(), LocalDateTime.now())
		);
	}


}
