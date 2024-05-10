package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.view.AreaSerializableView;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.app.utils.mock.AreaMock;
import com.github.projetoifsc.estagios.core.IAreaUseCases;
import com.github.projetoifsc.estagios.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// Aí aqui no app, que tá usando o Spring, eu posso colocar um arquivo de configuração
// Onde eu vou dizer qual a dependência pra cada um desses serviços
// O Spring carrega e injeta as dependências pra mim, como tem que ser
// Então tem que ver agora o que é componente e o que é configuração
// Esse é o próximo passo para eu aprender

@Service
public class AreaService {

    IAreaUseCases areaUseCases;
    Mapper mapper;

    @Autowired
    public AreaService(IAreaUseCases areaUseCases, Mapper mapper) {
        this.areaUseCases = areaUseCases;
        this.mapper = mapper;
    }

    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();

    public List<AreaSerializableView> getAll() {
        var areas = areaUseCases.getAll();
        return areas.stream()
                .map(area -> mapper.map(area, AreaSerializableView.class))
                .toList();
    }


    public AreaSerializableView getById(String id) {
        var area= areaUseCases.getById(id);
        var mapped = mapper.map(area, AreaSerializableView.class);
        return mapped;
    }


    private AreaSerializableView addHateoasLinks(AreaSerializableView area) {
        // TODO
        return null;
    }



}
