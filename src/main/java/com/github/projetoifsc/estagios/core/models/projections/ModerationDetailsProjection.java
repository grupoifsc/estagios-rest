package com.github.projetoifsc.estagios.core.models.projections;

import java.time.LocalDateTime;

public interface ModerationDetailsProjection {
    
    LocalDateTime getModifiedAt();
    ModerationStatusProjection getStatus();
    
    interface ModerationStatusProjection {
        String getName();
    }
    
}