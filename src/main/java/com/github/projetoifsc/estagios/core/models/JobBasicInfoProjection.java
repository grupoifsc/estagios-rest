package com.github.projetoifsc.estagios.core.models;

public interface JobBasicInfoProjection extends IJob {

    @Override
    String getId();

    @Override
    OrgBasicInfoProjection getOwner();

    String getTitulo();

}
