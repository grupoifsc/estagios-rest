package com.github.projetoifsc.estagios.core.models;

public interface IJob {

    String getId();
    void setId(String id);
    IOrg getOwner();
    void setOwner(IOrg user);
//    LocalDateTime getCreatedAt();

}
