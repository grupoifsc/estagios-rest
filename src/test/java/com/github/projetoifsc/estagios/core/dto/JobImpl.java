package com.github.projetoifsc.estagios.core.dto;

import com.github.projetoifsc.estagios.core.models.IArea;
import com.github.projetoifsc.estagios.core.models.IJobEntryData;
import com.github.projetoifsc.estagios.core.models.IOrganization;

import java.util.ArrayList;
import java.util.List;

public class JobImpl implements IJobEntryData {

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
    public List<String> getAreasIds() {
        return List.of();
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
    public String getContactId() {
        return "";
    }

    @Override
    public String getAddressId() {
        return "";
    }

    //@Override
    public List<IArea> getAreas() {
        return areas;
    }

}
