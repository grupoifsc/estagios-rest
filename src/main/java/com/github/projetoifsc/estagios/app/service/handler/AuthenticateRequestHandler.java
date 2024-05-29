package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.model.response.View;

public class AuthenticateRequestHandler extends RequestHandler{
    @Override
    public void handle(View view) {
        // Authenticate
        super.next(view);
    }
}
