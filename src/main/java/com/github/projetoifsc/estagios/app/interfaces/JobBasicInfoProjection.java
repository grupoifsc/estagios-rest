package com.github.projetoifsc.estagios.app.interfaces;

import com.github.projetoifsc.estagios.core.IJob;

import java.time.LocalDateTime;

public interface JobBasicInfoProjection extends IJob {

    @Override
    String getId();

    @Override
    OrgBasicInfoProjection getOwner();

    String getTitulo();

}
