package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.model.response.View;

public class AuthorizeRequestHandler extends RequestHandler {


    @Override
    public void handle(View view) {
        // Authorize
        super.next(view);
    }


}
