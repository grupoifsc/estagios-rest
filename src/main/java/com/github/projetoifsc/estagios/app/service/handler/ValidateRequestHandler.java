package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.view.SerializableView;

public class ValidateRequestHandler extends RequestHandler{

    @Override
    public void handle(SerializableView serializableView) {
        serializableView.validate();
        next(serializableView);
    }

}
