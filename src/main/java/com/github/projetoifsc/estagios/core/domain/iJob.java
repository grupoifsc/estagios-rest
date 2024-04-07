package com.github.projetoifsc.estagios.core.domain;

import java.util.List;

public interface iJob {

    String getId();
    void setId(String id);
    iOrganization getOwner();
    void setOwner(iOrganization user);
    List<String> getReceiversIds();
    void setReceiversIds(List<String> receiversIds);

}
