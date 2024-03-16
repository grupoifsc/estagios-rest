package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.dto.DTO;

public class AuthenticateRequestHandler extends RequestHandler{
    @Override
    public void handle(DTO dto) {
        // Authenticate
        super.next(dto);
    }
}
