package com.github.projetoifsc.estagios.app.security.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
class CustomUserDetailService implements UserDetailsService {

    private final AuthenticationService authenticationService;


    @Autowired
    public CustomUserDetailService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = authenticationService.findByUsername(username).orElseThrow();
        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAuthorities()
        );
    }


}
