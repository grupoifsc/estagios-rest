package com.github.projetoifsc.estagios.infra.db.jpa;

import com.github.projetoifsc.estagios.app.interfaces.CustomUserDetails;
import com.github.projetoifsc.estagios.app.security.IAuthenticationDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
class AuthenticationDBImpl implements IAuthenticationDB {

    private final UserCredentialsRepository userCredentialsRepository;

    @Autowired
    public AuthenticationDBImpl(UserCredentialsRepository userCredentialsRepository) {
        this.userCredentialsRepository = userCredentialsRepository;
    }

    // TODO: Checar se está funcionando mesmo quando retorna nullo
    public Optional<CustomUserDetails> findByUsername(String username) {
        return userCredentialsRepository.findByEmail(username)
                .map(this::mapAuthUserToCustomUserDetails);
    }

    private CustomUserDetails mapAuthUserToCustomUserDetails(UserCredentialsEntity user) {
        var customUserDetails = new AuthUserDetailsDTO();
        customUserDetails.setUsername(user.getEmail());
        customUserDetails.setId(String.valueOf(user.getId()));
        customUserDetails.setPassword(user.getPassword());
        customUserDetails.addAuthority(user.getRole());
        return customUserDetails;
    }

}
