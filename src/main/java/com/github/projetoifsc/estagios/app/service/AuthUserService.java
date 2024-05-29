package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.infra.auth.AuthUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

// TODO: Aqui fazer a conexão com o banco de dados
// Tanto para criar como para recuperar usuário!
@Service
public class AuthUserService {

    private final PasswordEncoder passwordEncoder;

    private final String ADMIN_EMAIL = "admin@teste.com";
    private final String USER_EMAIL = "user@teste.com";

    @Autowired
    public AuthUserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<AuthUser> findByEmail(String email) {
        if(email.equalsIgnoreCase(ADMIN_EMAIL)) {
            var user = new AuthUser();
            user.setEmail(ADMIN_EMAIL);
            user.setId(1L);
            user.setExtraInfo("Administrador");
            user.setPassword(passwordEncoder.encode("senha"));
            user.setRole("ROLE_ADMIN");
            return Optional.of(user);
        } else if(email.equalsIgnoreCase(USER_EMAIL)) {
            var user = new AuthUser();
            user.setEmail(USER_EMAIL);
            user.setId(99L);
            user.setExtraInfo("Basic User");
            user.setPassword(passwordEncoder.encode("senha"));
            user.setRole("ROLE_USER");
            return Optional.of(user);
        }
        return Optional.empty();
    }

}
