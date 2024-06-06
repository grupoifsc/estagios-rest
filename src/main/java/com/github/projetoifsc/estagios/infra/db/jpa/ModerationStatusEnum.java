package com.github.projetoifsc.estagios.infra.db.jpa;

enum ModerationStatusEnum {
    APPROVED((short) 1, "aprovado"),
    REJECTED((short) 2, "rejeitado");

    private final short id;
    private final String status;

    ModerationStatusEnum(short id, String status) {
        this.id = id;
        this.status = status;
    }

    public short getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

}
