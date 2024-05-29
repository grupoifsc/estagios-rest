package com.github.projetoifsc.estagios.app.security;

import com.github.projetoifsc.estagios.app.interfaces.CustomUserDetails;

import java.util.Optional;

public interface IAuthenticationDB {

    Optional<CustomUserDetails> findByUsername(String username);

}
