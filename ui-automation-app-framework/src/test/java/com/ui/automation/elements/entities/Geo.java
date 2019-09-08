package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Geo {

    @SerializedName("id_cc")
    @Expose
    private Integer idCc;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getIdCc() {
        return idCc;
    }

    public void setIdCc(Integer idCc) {
        this.idCc = idCc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}