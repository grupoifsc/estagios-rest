package com.github.projetoifsc.estagios.app.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.projetoifsc.estagios.app.model.interfaces.JobPrivateSummaryProjection;
import com.github.projetoifsc.estagios.app.model.interfaces.OrgBasicInfoProjection;

@JsonPropertyOrder(value = {"id", "titulo",
        "requisitos", "periodo", "carga_horaria", "remuneracao",
        "nivel", "formato", "modificado_em", "_links"})
public class PrivateJobSummaryResponse extends PublicJobSummaryResponse implements JobPrivateSummaryProjection {

    @JsonIgnore
    private OrgBasicInfoProjection owner;

    @JsonProperty(value = "periodo")
    private short periodId;

    @JsonProperty(value = "nivel")
    private short levelId;

    @JsonProperty("formato")
    private short formatId;


    @Override
    public short getPeriodId() {
        return periodId;
    }

    public void setPeriodId(short periodId) {
        this.periodId = periodId;
    }

    @Override
    public short getLevelId() {
        return levelId;
    }

    public void setLevelId(short levelId) {
        this.levelId = levelId;
    }

    @Override
    public short getFormatId() {
        return formatId;
    }

    public void setFormatId(short formatId) {
        this.formatId = formatId;
    }

}
