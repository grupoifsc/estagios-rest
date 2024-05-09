package com.github.projetoifsc.estagios;

import java.util.List;

/**
 * Projection for {@link com.github.projetoifsc.estagios.infra.db.jpa.OrganizationEntity}
 */
public interface OrganizationEntityInfo {
    Long getId();

    String getNome();

    boolean isIe();

    String getInfo();

    String getWebsite();

    String getRedesSociais();

    List<ContactMainInfo> getMainContact();

    List<AddressMainInfo> getMainAddress();

    /**
     * Projection for {@link com.github.projetoifsc.estagios.infra.db.jpa.ContactMain}
     */
    interface ContactMainInfo {
        String getEmail();

        String getTelefone();
    }

    /**
     * Projection for {@link com.github.projetoifsc.estagios.infra.db.jpa.AddressMain}
     */
    interface AddressMainInfo {
        String getRua();

        String getBairro();

        String getCidade();

        String getEstado();

        String getPais();
    }
}