package com.github.projetoifsc.estagios.core.models;

import java.time.LocalDateTime;

public interface ModerationProjection {
    
    LocalDateTime getModifiedAt();
    ModerationStatusProjection getStatus();
    
    interface ModerationStatusProjection {
        String getName();
    }
    
}