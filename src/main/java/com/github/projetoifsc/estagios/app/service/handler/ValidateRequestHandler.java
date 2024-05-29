package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.model.response.View;

public class ValidateRequestHandler extends RequestHandler{

    @Override
    public void handle(View view) {
        view.validate();
        next(view);
    }

}
