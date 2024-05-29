package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.model.response.View;

public class RemoveLinksRequestHandler extends RequestHandler{
    @Override
    public void handle(View view) {
        view.removeLinks();
        super.next(view);
    }
}
