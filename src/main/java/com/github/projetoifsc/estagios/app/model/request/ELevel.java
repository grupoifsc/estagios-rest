package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ELevel {

    FUNDAMENTAL(1, "fundamental"),
    MEDIO(2, "medio"),
    TECNICO(3, "tecnico"),
    GRADUACAO(4, "graduacao"),
    POS(5, "pos");

    private final int id;
    private final String name;

    ELevel(int id, String name) {
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

    public static ELevel findByName(String name) {
        for(ELevel level : ELevel.values()) {
            if(level.name.equalsIgnoreCase(name))
                return level;
        }
        return null;
    }

    public static ELevel findById(short id) {
        for(ELevel level : ELevel.values()) {
            if(level.id == id)
                return level;
        }
        return null;
    }

}
