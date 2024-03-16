package com.github.projetoifsc.estagios.app.utils.hateoas;

import com.github.projetoifsc.estagios.app.dto.UserDTO;


public class UserHateoasHelper {

    // Diretamente dependente do Controller

    public static <T extends UserDTO> void addUserPrivateProfileLinks(T userDTO) {
        //userDTO.add();
    }

    public static <T extends UserDTO> void addUserPublicProfileLinks(T userDTO) {

    }

}
