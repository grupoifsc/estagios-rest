package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.app.dto.OrgDTO;
import com.github.projetoifsc.estagios.app.dto.OrgPrivateProfileDTO;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.core.dto.OrganizationImpl;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class JavaBasicsTest {

    @Test
    void polimorfismoComListas() {

        IOrganization orgA = new OrgDTO();
        IOrganization orgB = new OrgPrivateProfileDTO();
        IOrganization orgC = new OrganizationImpl("1", true);

        List<IOrganization> orgs = new ArrayList<>();
        orgs.add(orgA);
        orgs.add(orgB);
        orgs.add(orgC);

        List<IOrganization> organizations;
        List<OrgDTO> orgDTOList = new ArrayList<>();
        var orgDTO = new OrgDTO();
        orgDTO.setId("1");
        orgDTOList.add(orgDTO);
        // Por que isso dá erro?
        // organizations = orgDTOList;
        // A coleção não é intercambiável por causa das restrições dos tipos que são aceitos
        // pelos métodos da coleção
        // Uma lista de OrgDTOs só aceita OrgDTOs

        // Uma solução possível:
        organizations = new ArrayList<>(orgDTOList);

        List<OrgPrivateProfileDTO> orgPrivateProfileDTOList = new ArrayList<>();
        orgPrivateProfileDTOList.add(new OrgPrivateProfileDTO());

        organizations.addAll(orgPrivateProfileDTOList);

        System.out.println(organizations);
        System.out.println(organizations.size());
        organizations.forEach(col -> System.out.println(col));

    }

    @Test
    void someLogic() {

        List<String> ids = new ArrayList<>(List.of("1","2", "3", "1000"));

        List<String> found = new ArrayList<>(List.of("1", "3"));


        if(ids.size() == found.size()) {
            System.out.println("Encontrou tudo!!!");
            return;
        }

        List<String> notfound = new ArrayList<>();
        for(String org : found) {
            for(String id : ids) {
                if(org.equalsIgnoreCase(id)) {
                    ids.remove(id);
                    break;
                }
            }

        }

        System.out.println("Not found: ");
        System.out.println(ids);

    }

}
