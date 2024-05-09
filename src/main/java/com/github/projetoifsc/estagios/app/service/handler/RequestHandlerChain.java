package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.view.SerializableView;

public class RequestHandlerChain {

    private RequestHandler authenticate = new AuthenticateRequestHandler();
    private RequestHandler authorize = new AuthorizeRequestHandler();
    private RequestHandler removeLinks = new RemoveLinksRequestHandler();
    private RequestHandler validate = new ValidateRequestHandler();

    public RequestHandlerChain() {
        authenticate.setNext(authorize);
        authorize.setNext(removeLinks);
        removeLinks.setNext(validate);
    }

    public void handle(SerializableView serializableView) {
        authenticate.handle(serializableView);
    }

}
