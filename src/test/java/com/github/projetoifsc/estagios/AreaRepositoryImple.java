package com.github.projetoifsc.estagios;

import com.github.javafaker.Faker;
import com.github.projetoifsc.estagios.core.IArea;
import com.github.projetoifsc.estagios.core.IAreaRepository;
import com.github.projetoifsc.estagios.core.IOrganization;
import com.github.projetoifsc.estagios.infra.db.jpa.AreaMocker;

import java.util.ArrayList;
import java.util.List;

class AreaRepositoryImple implements IAreaRepository {

    Faker faker = new Faker();
    AreaMocker areaMocker = new AreaMocker(faker);

    @Override
    public IArea create(IArea area) {
        return null;
    }

    @Override
    public IOrganization getOwner(IArea area) {
        return null;
    }

    @Override
    public List<IArea> getAll() {
        List<IArea> areas = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            areas.add(areaMocker.generate());
        }
        return areas;
    }

    @Override
    public IArea getById(String id) {
        return null;
    }
}
