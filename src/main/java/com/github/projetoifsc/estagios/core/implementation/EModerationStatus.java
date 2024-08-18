package com.github.projetoifsc.estagios.core.implementation;

enum EModerationStatus {

    ACCEPTED("aprovado"),
    REJECTED("rejeitado"),
    PENDING("pendente"),
    OWNER("criador - moderação não disponível");

    private final String namePtBR;

    EModerationStatus(String namePtBR) {
        this.namePtBR = namePtBR;
    }

    public String getNamePtBR() {
        return namePtBR;
    }

}
