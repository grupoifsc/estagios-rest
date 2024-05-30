package com.github.projetoifsc.estagios.app.security.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface CustomUserDetails extends UserDetails {
    String getId();
}
