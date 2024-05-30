package com.github.projetoifsc.estagios.app.controller;

import com.github.projetoifsc.estagios.app.exception.InvalidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/teste/exception/internal_error")
    public void excecao() throws Exception {
        throw new Exception("Erro Interno");
    }

    @GetMapping("/teste/exception/invalid")
    public void invalidException() {
        throw new InvalidException("Dados errados");
    }


}
