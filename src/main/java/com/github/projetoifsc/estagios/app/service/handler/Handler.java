package com.github.projetoifsc.estagios.app.service.handler;


// TODO
// RequestHandler
// Vem o Request com as coisas de autenticação
// Valida entrada de dados de autenticação
// Autentica e autoriza básico
// Valida entrada de dados
// Remove links que possam ter vindo
// SERVIÇO
// Adiciona Links Hateoas


import com.github.projetoifsc.estagios.app.view.SerializableView;

public interface Handler {

    void setNext(Handler handler);
    void handle(SerializableView serializableView);


}
