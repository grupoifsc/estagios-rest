package com.github.projetoifsc.estagios.core.domain.dto;

import com.github.projetoifsc.estagios.core.domain.iJob;
import com.github.projetoifsc.estagios.core.domain.iOrganization;

import java.util.ArrayList;
import java.util.List;

public class JobImpl implements iJob {

    private String id;
    private iOrganization owner;
    private List<String> receivers = new ArrayList<>();

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public iOrganization getOwner() {
        return owner;
    }

    @Override
    public void setOwner(iOrganization owner) {
        this.owner = owner;
    }

    @Override
    public List<String> getReceiversIds() {
        return receivers;
    }

    @Override
    public void setReceiversIds(List<String> receivers) {
        this.receivers = receivers;
    }

}
