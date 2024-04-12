package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IJob {

    String getId();
    void setId(String id);
    IOrganization getOwner();
    void setOwner(IOrganization user);
    List<String> getReceiversIds();
    void setReceiversIds(List<String> receiversIds);
    List<IArea> getAreas();

}
