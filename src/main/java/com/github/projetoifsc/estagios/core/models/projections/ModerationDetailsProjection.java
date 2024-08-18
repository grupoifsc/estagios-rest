package com.github.projetoifsc.estagios.core.models.projections;

import java.time.LocalDateTime;

public interface ModerationDetailsProjection {
    
    LocalDateTime getModifiedAt();
    String getStatus();
    void setStatus(String status);

}