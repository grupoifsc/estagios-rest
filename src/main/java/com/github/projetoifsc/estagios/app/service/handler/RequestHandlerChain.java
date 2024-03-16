package com.github.projetoifsc.estagios.app.service.handler;

import com.github.projetoifsc.estagios.app.dto.DTO;

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

    public void handle(DTO dto) {
        authenticate.handle(dto);
    }

}
