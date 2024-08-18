package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.core.models.projections.ModerationDetailsProjection;

import java.time.LocalDateTime;

@JsonPropertyOrder({"status", "modificado_em"})
public class ModerationDetail implements ModerationDetailsProjection {

    @JsonProperty(value = "modificado_em")
    private LocalDateTime modifiedAt;

    private String status;

    @Override
    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
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
}
