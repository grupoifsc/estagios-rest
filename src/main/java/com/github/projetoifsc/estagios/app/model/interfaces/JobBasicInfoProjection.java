package com.github.projetoifsc.estagios.app.model.interfaces;

import com.github.projetoifsc.estagios.core.IJob;

public interface JobBasicInfoProjection extends IJob {

    @Override
    String getId();

    @Override
    OrgBasicInfoProjection getOwner();

    String getTitulo();

}
