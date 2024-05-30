package com.github.projetoifsc.estagios.core;

import java.util.List;

public interface IJobEntryData extends IJob {

    List<String> getAreasIds();
    List<String> getReceiversIds();
    void setReceiversIds(List<String> receivers);
    String getContactId();
//    void setContactId(String contactId);
    String getAddressId();

}
