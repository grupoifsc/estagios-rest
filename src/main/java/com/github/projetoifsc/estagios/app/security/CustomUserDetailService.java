package com.github.projetoifsc.estagios.app.security;

import com.github.projetoifsc.estagios.app.service.AuthUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {

    private final AuthUserService authUserService;

    @Autowired
    public CustomUserDetailService(AuthUserService authUserService) {
        this.authUserService = authUserService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = authUserService.findByEmail(username).orElseThrow();
        var userPrincipal = new UserPrincipal(
                user.getId(),
                user.getEmail(),
                List.of(new SimpleGrantedAuthority(user.getRole()))
        );
        userPrincipal.setPassword(user.getPassword());
        System.out.println(userPrincipal.getAuthorities());
        return userPrincipal;
    }

}
