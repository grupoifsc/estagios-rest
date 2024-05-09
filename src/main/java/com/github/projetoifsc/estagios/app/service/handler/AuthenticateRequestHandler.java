package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.view.SerializableView;

public class AuthenticateRequestHandler extends RequestHandler{
    @Override
    public void handle(SerializableView serializableView) {
        // Authenticate
        super.next(serializableView);
    }
}
