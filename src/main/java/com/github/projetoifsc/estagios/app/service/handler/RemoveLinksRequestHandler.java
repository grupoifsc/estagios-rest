package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.dto.DTO;

public class RemoveLinksRequestHandler extends RequestHandler{
    @Override
    public void handle(DTO dto) {
        dto.removeLinks();
        super.next(dto);
    }
}
