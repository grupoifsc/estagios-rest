package com.github.projetoifsc.estagios.core.domain;

import java.util.List;

public interface ITraineeship {

    String getId();
    void setId(String id);
    IOrganization getOwner();
    void setOwner(IOrganization user);
    List<String> getReceiversIds();
    void setReceiversIds(List<String> receiversIds);

}
