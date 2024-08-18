package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;

import java.time.LocalDateTime;

class ModJobDTO implements ModerationDetailsProjection {

    private String status;
    private LocalDateTime modifiedAt;

    public ModJobDTO() {}

    public ModJobDTO(String status, LocalDateTime modifiedAt) {
        this.status = status;
        this.modifiedAt = modifiedAt;
    }

    @Override
    public String getStatus() {
        return status;
    }

    @Override
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

}
