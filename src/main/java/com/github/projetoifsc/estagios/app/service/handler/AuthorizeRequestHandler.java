package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.dto.DTO;

public class AuthorizeRequestHandler extends RequestHandler {


    @Override
    public void handle(DTO dto) {
        // Authorize
        super.next(dto);
    }


}
