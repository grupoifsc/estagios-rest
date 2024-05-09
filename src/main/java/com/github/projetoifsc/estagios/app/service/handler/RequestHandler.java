package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.view.SerializableView;

public abstract class RequestHandler implements Handler {

    protected Handler next;

    @Override
    public void setNext(Handler next) {
        this.next = next;
    }

    @Override
    public void handle(SerializableView serializableView) {
        // Handle e passar adiante
        next(serializableView);
    }

    protected void next(SerializableView serializableView) {
        if (this.next != null) {
            next.handle(serializableView);
        }
    }

}
