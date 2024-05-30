package com.github.projetoifsc.estagios.app.security.auth;

import java.util.Optional;

public interface IAuthenticationDAO {

    Optional<CustomUserDetails> findByUsername(String username);

}
