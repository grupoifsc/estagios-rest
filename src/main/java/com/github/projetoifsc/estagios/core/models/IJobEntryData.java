package com.github.projetoifsc.estagios.core.models;

import java.util.List;


public interface IJobEntryData extends IJob {

    List<String> getAreasIds();
    List<String> getReceiversIds();
    void setReceiversIds(List<String> receivers);
    String getContactId();
    String getAddressId();

    // Aqui entra aquela questão do que é estritamente necessário ou não para a lógica
    // Essas info abaixo não são usadas aqui...
    short getPeriodId();
    short getFormatId();
    short getLevelId();

}
