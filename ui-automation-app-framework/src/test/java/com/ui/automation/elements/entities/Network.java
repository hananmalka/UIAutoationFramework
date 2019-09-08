package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Network {

    @SerializedName("source")
    @Expose
    private Object source;
    @SerializedName("id_status")
    @Expose
    private Integer idStatus;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("went_live")
    @Expose
    private String wentLive;

    public Object getSource() {
        return source;
    }

    public void setSource(Object source) {
        this.source = source;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getWentLive() {
        return wentLive;
    }

    public void setWentLive(String wentLive) {
        this.wentLive = wentLive;
    }
}