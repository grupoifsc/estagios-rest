package com.github.projetoifsc.estagios.app.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.projetoifsc.estagios.app.interfaces.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public record UserPrincipal (String id, String username, String password, Collection<? extends GrantedAuthority> authorities) implements CustomUserDetails {

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return CustomUserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return CustomUserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return CustomUserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return CustomUserDetails.super.isEnabled();
    }

}
