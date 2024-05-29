package com.github.projetoifsc.estagios.app.interfaces;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getId();
}
