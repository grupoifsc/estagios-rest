package com.github.projetoifsc.estagios.app.utils.hateoas;

import com.github.projetoifsc.estagios.app.controller.OrgController;
import com.github.projetoifsc.estagios.app.controller.VagaController;
import com.github.projetoifsc.estagios.app.view.VagaSerializableView;
import com.github.projetoifsc.estagios.app.view.VagaPrivateProfileSerializableView;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

public class VagaHateoasHelper {

    private static Class<VagaController> vagaController = VagaController.class;

    public static void addPublicProfileLinks(VagaSerializableView vaga) {

        vaga.add(linkTo(methodOn(vagaController)
                .getPublicProfile(vaga.getId())).withSelfRel());
        vaga.add(linkTo(methodOn(OrgController.class)
                .getUserPublicProfile(vaga.getOwner().getId())).withRel("criador"));

    }


    public static void addPrivateProfileLinks(VagaSerializableView vaga) {

        vaga.add(linkTo(methodOn(vagaController)
                .getPrivateProfile(vaga.getId())).withSelfRel()
                .andAffordance(afford(methodOn(vagaController
                )
                        .create(new VagaPrivateProfileSerializableView())))
                .andAffordance(afford(methodOn(vagaController)
                        .update(vaga.getId(), new VagaPrivateProfileSerializableView())))
                .andAffordance(afford(methodOn(vagaController)
                        .delete(vaga.getId())))
        );
        vaga.add(linkTo(methodOn(OrgController.class)
                .getAuthUserPerfil(vaga.getOwner().getId())).withRel("criador"));
        vaga.add(linkTo(methodOn(vagaController)
                .getVagaRecipients(vaga.getId())).withRel("destinatarios"));


    }


}
