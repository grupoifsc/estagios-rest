package com.github.projetoifsc.estagios.core.domain.dto;

import com.github.projetoifsc.estagios.core.domain.ITraineeship;
import com.github.projetoifsc.estagios.core.domain.IOrganization;

import java.util.ArrayList;
import java.util.List;

public class TraineeshipImpl implements ITraineeship {

    private String id;
    private IOrganization owner;
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
    public IOrganization getOwner() {
        return owner;
    }

    @Override
    public void setOwner(IOrganization owner) {
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
