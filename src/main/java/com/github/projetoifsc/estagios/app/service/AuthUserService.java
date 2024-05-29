package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.interfaces.CustomUserDetails;
import com.github.projetoifsc.estagios.app.security.IAuthenticationDB;
import com.github.projetoifsc.estagios.app.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// TODO: Aqui fazer a conexão com o banco de dados
// Tanto para criar como para recuperar usuário!
@Service
public class AuthUserService {

    private final PasswordEncoder passwordEncoder;
    private final IAuthenticationDB authenticationDB;

    private final String ADMIN_EMAIL = "admin@teste.com";
    private final String USER_EMAIL = "user@teste.com";


    @Autowired
    public AuthUserService(PasswordEncoder passwordEncoder, IAuthenticationDB authenticationDB) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationDB = authenticationDB;
    }

    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public Optional<CustomUserDetails> findByUsername(String username) {
        if(username.equalsIgnoreCase(ADMIN_EMAIL) || username.equalsIgnoreCase(USER_EMAIL))
            return findMockedByEmail(username);
        return authenticationDB.findByUsername(username);
    }

    public Optional<CustomUserDetails> findMockedByEmail(String email) {
        if(email.equalsIgnoreCase(ADMIN_EMAIL)) {
            var password = passwordEncoder.encode("senha");
            var user = new UserPrincipal("1", ADMIN_EMAIL, password, List.of(new SimpleGrantedAuthority("ROLE_ADMIN")));
            return Optional.of(user);
        }
        var password = passwordEncoder.encode("senha");
        var user = new UserPrincipal("99", USER_EMAIL, password, List.of(new SimpleGrantedAuthority("ROLE_USER")));
        return Optional.of(user);
    }

}
