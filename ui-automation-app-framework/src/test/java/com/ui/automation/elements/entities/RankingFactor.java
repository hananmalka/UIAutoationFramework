package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RankingFactor{

    @SerializedName("id_campaign_ranking_factor")
    @Expose
    private Integer idCampaignRankingFactor;
    @SerializedName("id_campaign")
    @Expose
    private Integer idCampaign;
    @SerializedName("id_publisher")
    @Expose
    private Integer idPublisher;
    @SerializedName("id_factor")
    @Expose
    private Integer idFactor;
    @SerializedName("expiration_date")
    @Expose
    private Object expirationDate;
    @SerializedName("last_update")
    @Expose
    private String lastUpdate;

    public Integer getIdCampaignRankingFactor() {
        return idCampaignRankingFactor;
    }

    public void setIdCampaignRankingFactor(Integer idCampaignRankingFactor) {
        this.idCampaignRankingFactor = idCampaignRankingFactor;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Integer getIdPublisher() {
        return idPublisher;
    }

    public void setIdPublisher(Integer idPublisher) {
        this.idPublisher = idPublisher;
    }

    public Integer getIdFactor() {
        return idFactor;
    }

    public void setIdFactor(Integer idFactor) {
        this.idFactor = idFactor;
    }

    public Object getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Object expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}