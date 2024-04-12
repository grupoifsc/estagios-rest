package com.github.projetoifsc.estagios.app.service;

import com.github.projetoifsc.estagios.app.dto.AreaDTO;
import com.github.projetoifsc.estagios.app.service.handler.RequestHandlerChain;
import com.github.projetoifsc.estagios.app.utils.mock.AreaMock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

// Aí aqui no app, que tá usando o Spring, eu posso colocar um arquivo de configuração
// Onde eu vou dizer qual a dependência pra cada um desses serviços
// O Spring carrega e injeta as dependências pra mim, como tem que ser
// Então tem que ver agora o que é componente e o que é configuração
// Esse é o próximo passo para eu aprender


@Service
public class AreaService {

    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();

    public ResponseEntity<Page<AreaDTO>> getAll() {
        // Aqui a ideia é que o serviço a ser usado pode mudar...
        return new ResponseEntity<>(
                new PageImpl<>(AreaMock.getList()),
                HttpStatus.OK
        );
    }

    private AreaDTO addHateoasLinks(AreaDTO area) {
        return area;
    }

}
