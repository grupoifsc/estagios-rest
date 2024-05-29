package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.model.response.View;

public abstract class RequestHandler implements Handler {

    protected Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(View view) {
        // Handle e passar adiante
        next(view);
    }

    protected void next(View view) {
        if (this.next != null) {
            next.handle(view);
        }
    }

}
