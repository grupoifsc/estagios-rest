package com.github.projetoifsc.estagios.core.models;


import java.time.LocalDateTime;

public interface IJob {

    String getId();
    void setId(String id);
    IOrganization getOwner();
    void setOwner(IOrganization user);
    LocalDateTime getCreatedAt();

}
