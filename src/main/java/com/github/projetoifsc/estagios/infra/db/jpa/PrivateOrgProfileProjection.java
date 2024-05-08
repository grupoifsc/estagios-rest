package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.IOrganization;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Projection for {@link OrganizationEntity}
 */
public interface PrivateOrgProfileProjection extends OrgBasicInfoProjection {
    String getId();

    String getUsername();

    String getNome();

    String getCnpj();

    Boolean getIe();

    String getInfo();

    String getWebsite();

    String getRedesSociais();

    LocalDateTime getCreatedAt();

    LocalDateTime getUpdatedAt();

    ContactInfo getMainContact();

    ContactInfo getApplianceContact();

    AddressMainInfo getMainAddress();

    /**
     * Projection for {@link ContactMain}
     */
    interface ContactInfo {
        String getEmail();
        String getTelefone();
    }

    /**
     * Projection for {@link AddressMain}
     */
    interface AddressMainInfo {
        String getRua();
        String getBairro();
        String getCidade();
        String getEstado();
        String getPais();
    }
}