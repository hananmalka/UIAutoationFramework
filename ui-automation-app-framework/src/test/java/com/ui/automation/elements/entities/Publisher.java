package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Publisher {

    @SerializedName("internal_publisher_id")
    @Expose
    private String internalPublisherId;
    @SerializedName("id_publisher")
    @Expose
    private Integer idPublisher;
    @SerializedName("id_status")
    @Expose
    private Integer idStatus;

    public String getInternalPublisherId() {
        return internalPublisherId;
    }

    public void setInternalPublisherId(String internalPublisherId) {
        this.internalPublisherId = internalPublisherId;
    }

    public Integer getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Integer idPublisher) {
        this.idPublisher = idPublisher;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

}