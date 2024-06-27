package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EFormat {

    PRESENCIAL(1, "presencial"),
    HIBRIDO(2, "hibrido"),
    REMOTO(3, "remoto");

    private final int id;
    private final String name;

    EFormat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public static EFormat findByName(String name) {
        for(EFormat format : EFormat.values()) {
            if(format.name.equalsIgnoreCase(name))
                return format;
        }
        return null;
    }

    public static EFormat findById(short id) {
        for(EFormat format : EFormat.values()) {
            if(format.id == id) {
                return format;
            }
        }
        return null;
    }


}
