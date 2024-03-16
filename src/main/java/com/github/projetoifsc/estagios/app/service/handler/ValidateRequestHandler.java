package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.dto.DTO;

public class ValidateRequestHandler extends RequestHandler{

    @Override
    public void handle(DTO dto) {
        dto.validate();
        next(dto);
    }

}
