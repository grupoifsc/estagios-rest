package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.view.SerializableView;

public class RemoveLinksRequestHandler extends RequestHandler{
    @Override
    public void handle(SerializableView serializableView) {
        serializableView.removeLinks();
        super.next(serializableView);
    }
}
