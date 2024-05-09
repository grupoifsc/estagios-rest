package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.view.SerializableView;

public class AuthorizeRequestHandler extends RequestHandler {


    @Override
    public void handle(SerializableView serializableView) {
        // Authorize
        super.next(serializableView);
    }


}
