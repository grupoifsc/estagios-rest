package com.github.projetoifsc.estagios.app.model.request;

import com.fasterxml.jackson.annotation.JsonValue;

public enum EPeriod {
    MATUTINO(1, "matutino"),
    VESPERTINO(2, "vespertino"),
    NOTURNO(3, "noturno");

    private final int id;
    private final String name;

    EPeriod(int id, String name) {
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

    public static EPeriod findByName(String name) {
        for(EPeriod period : EPeriod.values()) {
            if(period.name.equalsIgnoreCase(name))
                return period;
        }
        return null;
    }

    public static EPeriod findById(short id) {
        for(EPeriod period : EPeriod.values()) {
            if(period.id == id)
                return period;
        }
        return null;
    }


}
