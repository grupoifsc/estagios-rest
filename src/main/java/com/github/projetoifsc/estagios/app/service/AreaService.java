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

@Service
public class AreaService {

    RequestHandlerChain requestHandlerChain = new RequestHandlerChain();

    public ResponseEntity<Page<AreaDTO>> getAll() {
        return new ResponseEntity<>(
                new PageImpl<>(AreaMock.getList()),
                HttpStatus.OK
        );
    }

    private AreaDTO addHateoasLinks(AreaDTO area) {
        return area;
    }

}
