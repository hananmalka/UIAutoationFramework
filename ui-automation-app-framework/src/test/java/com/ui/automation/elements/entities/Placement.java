package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Placement {

    @SerializedName("id_campaign_placement")
    @Expose
    private Integer idCampaignPlacement;
    @SerializedName("id_campaign")
    @Expose
    private Integer idCampaign;
    @SerializedName("id_publisher")
    @Expose
    private Object idPublisher;
    @SerializedName("id_placement")
    @Expose
    private Integer idPlacement;
    @SerializedName("percentage")
    @Expose
    private Integer percentage;

    public Integer getIdCampaignPlacement() {
        return idCampaignPlacement;
    }

    public void setIdCampaignPlacement(Integer idCampaignPlacement) {
        this.idCampaignPlacement = idCampaignPlacement;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Object getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Object idPublisher) {
        this.idPublisher = idPublisher;
    }

    public Integer getIdPlacement() {
        return idPlacement;
    }

    public void setIdPlacement(Integer idPlacement) {
        this.idPlacement = idPlacement;
    }

    public Integer getPercentage() {
        return percentage;
    }

    public void setPercentage(Integer percentage) {
        this.percentage = percentage;
    }

}