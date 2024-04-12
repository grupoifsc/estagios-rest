package com.github.projetoifsc.estagios;

import com.github.projetoifsc.estagios.core.IAreaUseCases;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientTest {

    IAreaUseCases areaUseCases;

    @Autowired
    ClientTest(IAreaUseCases areaUseCases) {
        this.areaUseCases = areaUseCases;
    }

    @Test
    void testar() {
        System.out.println(areaUseCases.getAll());
    }

}
