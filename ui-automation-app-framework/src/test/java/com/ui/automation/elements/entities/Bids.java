package com.ui.automation.elements.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Bids {

    @SerializedName("geos")
    @Expose
    private List<Geo> geos = null;
    @SerializedName("rate")
    @Expose
    private Integer rate;

    public List<Geo> getGeos() {
        return geos;
    }

    public void setGeos(List<Geo> geos) {
        this.geos = geos;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

}