package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.model.response.PublicAreaResponse;
import com.github.projetoifsc.estagios.app.security.auth.UserPrincipal;
import com.github.projetoifsc.estagios.core.IAreaUseCases;
import com.github.projetoifsc.estagios.app.utils.Mapper;
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

    private final IAreaUseCases areaUseCases;
    private final Mapper mapper;

    public AreaService(IAreaUseCases areaUseCases, Mapper mapper) {
        this.areaUseCases = areaUseCases;
        this.mapper = mapper;
    }


    public List<PublicAreaResponse> getAll(UserPrincipal userPrincipal) {
        var areas = areaUseCases.getAll();
        return areas.stream()
                .map(area -> mapper.map(area, PublicAreaResponse.class))
                .toList();
    }


    public PublicAreaResponse getById(UserPrincipal userPrincipal, String id) {
        var area= areaUseCases.getById(id);
        return mapper.map(area, PublicAreaResponse.class);
    }


}
