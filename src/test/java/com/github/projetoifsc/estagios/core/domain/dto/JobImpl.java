package com.github.projetoifsc.estagios.core.domain.dto;

import com.github.projetoifsc.estagios.core.IArea;
import com.github.projetoifsc.estagios.core.IJob;
import com.github.projetoifsc.estagios.core.IOrganization;

import java.util.ArrayList;
import java.util.List;

public class JobImpl implements IJob {

    private String id;
    private IOrganization owner;
    private List<String> receivers = new ArrayList<>();
    private List<IArea> areas = new ArrayList<>();

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

    @Override
    public List<IArea> getAreas() {
        return areas;
    }

}
