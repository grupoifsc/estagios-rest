package com.github.projetoifsc.estagios.app.utils.mock;

import com.github.projetoifsc.estagios.app.model.response.PublicAddressResponse;
import com.github.projetoifsc.estagios.app.model.response.PublicContactResponse;
import com.github.projetoifsc.estagios.app.model.response.PrivateJobDetailsResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VagaMock {

    private static final PrivateJobDetailsResponse vaga1 = new PrivateJobDetailsResponse();
    private static final PrivateJobDetailsResponse vaga2 = new PrivateJobDetailsResponse();
    private static final PrivateJobDetailsResponse vaga3 = new PrivateJobDetailsResponse();
    private static final List<PrivateJobDetailsResponse> vagas = new ArrayList<>(
            List.of(vaga1, vaga2, vaga3)
    );

    static {
        createVaga01();
        createVaga02();
        createVaga03();
    }

    private static void createVaga01() {
        vaga1.setId("1");
        vaga1.setOwner(OrgMock.getOne());
        vaga1.setTitulo("Vaga de Estágio Auxiliar de Sala");
        vaga1.setDescricao("Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga");
        vaga1.setRequisitos("Estudante de Pedagogia");
        vaga1.setPeriodId((short) 1);
        vaga1.setCargaHorariaSemanal(20);
        vaga1.setRemuneracao(1000);
        vaga1.setLevelId((short) 2);
        vaga1.setFormatId((short) 1);
        vaga1.setCreatedAt(LocalDateTime.now());
        vaga1.setUpdatedAt(LocalDateTime.now());
        vaga1.setDuracaoMeses(5);
        vaga1.setDataFinal(LocalDate.now().plusMonths(5));
        vaga1.setDataInicio(LocalDate.now());
        vaga1.setAddress(new PublicAddressResponse());
        vaga1.setContact(new PublicContactResponse());
    }

    private static void createVaga02() {
        vaga2.setId("2");
        vaga2.setOwner(OrgMock.getOne());
        vaga2.setTitulo("Auxiliar de Escritório");
        vaga2.setDescricao("Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga");
        vaga2.setRequisitos("Administração, Organização");
        vaga2.setPeriodId((short) 2);
        vaga2.setCargaHorariaSemanal(20);
        vaga2.setRemuneracao(700);
        vaga2.setLevelId((short) 3);
        vaga2.setFormatId((short) 3);
        vaga2.setCreatedAt(LocalDateTime.now());
        vaga2.setUpdatedAt(LocalDateTime.now());
        vaga2.setDuracaoMeses(12);
        vaga2.setDataFinal(LocalDate.now().plusMonths(12));
        vaga2.setDataInicio(LocalDate.now());
        vaga2.setAddress(new PublicAddressResponse());
        vaga2.setContact(new PublicContactResponse());
    }

    private static void createVaga03() {
        vaga3.setId("3");
        vaga3.setOwner(OrgMock.getOne());
        vaga3.setTitulo("Desenvolvimento Web");
        vaga3.setDescricao("Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga Descrição da Vaga");
        vaga3.setRequisitos("10 anos de experiência");
        vaga3.setPeriodId((short) 3);
        vaga3.setCargaHorariaSemanal(40);
        vaga3.setRemuneracao(500);
        vaga3.setLevelId((short) 5);
        vaga3.setFormatId((short) 1);
        vaga3.setCreatedAt(LocalDateTime.now());
        vaga3.setUpdatedAt(LocalDateTime.now());
        vaga3.setDuracaoMeses(10);
        vaga3.setDataFinal(LocalDate.now().plusMonths(10));
        vaga3.setDataInicio(LocalDate.now());
        vaga3.setAddress(new PublicAddressResponse());
        vaga3.setContact(new PublicContactResponse());
    }

    public static PrivateJobDetailsResponse getOne() {
        return vagas.get(0);
    }

    public static List<PrivateJobDetailsResponse> getList() {
        return vagas;
    }

    public static PrivateJobDetailsResponse getInvalid() {
        return new PrivateJobDetailsResponse();
    }


}
