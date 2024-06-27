package com.github.projetoifsc.estagios.infra.db.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.projetoifsc.estagios.app.security.auth.CustomUserDetails;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;

public class AuthUserDetailsDTO implements CustomUserDetails {

    private List<SimpleGrantedAuthority> authorities = new ArrayList<>();
    private String password;
    private String username;
    private String id;
    private boolean ie;

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<String> roles) {
        this.authorities = roles.stream().map(SimpleGrantedAuthority::new).toList();
    }

    public void addAuthority(String role) {
        var authority = role.transform(SimpleGrantedAuthority::new);
        authorities.add(authority);
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Boolean getIe() {
        return ie;
    }

    public void setIe(boolean ie) {
        this.ie = ie;
    }

}
