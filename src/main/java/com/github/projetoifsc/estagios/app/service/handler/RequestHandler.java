package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.dto.DTO;

public abstract class RequestHandler implements Handler {

    protected Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(DTO dto) {
        // Handle e passar adiante
        next(dto);
    }

    protected void next(DTO dto) {
        if (this.next != null) {
            next.handle(dto);
        }
    }

}
