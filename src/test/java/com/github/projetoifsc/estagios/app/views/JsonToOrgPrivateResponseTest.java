package com.github.projetoifsc.estagios.app.views;

import com.github.projetoifsc.estagios.app.model.response.OrgPrivateProfile;
import com.github.projetoifsc.estagios.app.utils.JsonParser;
import org.junit.jupiter.api.Test;


class JsonToOrgPrivateResponseTest {

    JsonParser jsonParser = new JsonParser();

    @Test
    void dataEntryWithoutValidation() {

        var username = "jujuju";

        var jsonString = "{" +
                "\"username\": \"" + username + "\", " +
                //"\"password\": \"root\"," +
                "\"nome\": \"Jurubebas\"," +
                "\"cnpj\": \"38088104000172\"," +
                "\"instituicao_de_ensino\": \"true\"," +
                "\"info\": \"Minha Companhia\"," +
                "\"contato_principal\": {" +
                "\"email\": \"ju@ju.com\"," +
                "\"telefone\": \"98655555666\"" +
        "}," +
        "\"contato_candidaturas\": {" +
            "\"email\": \"ju2@ju.com\"," +
            "\"telefone\": \"965555666\"" +
        "}," +
//        "\"endereco\": {" +
//            "\"rua\": \"<string>\"," +
//            "\"bairro\": \"<string>\"," +
//            "\"cidade\": \"<string>\"," +
//            "\"estado\": \"<string>\"," +
//            "\"pais\": \"<string>\"" +
//        "},"+
        "\"website\": \"www.juju.com.br\"," +
        "\"redes_sociais\": \"['juju']\"" + "}";

        var mapped = jsonParser.parseString(jsonString, OrgPrivateProfile.class);

        System.out.println(mapped.getUserCredentials().getEmail());
        System.out.println(mapped.getRedesSociais());

    }


}
