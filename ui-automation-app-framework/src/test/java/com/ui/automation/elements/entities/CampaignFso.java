package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CampaignFso {

    @SerializedName("id_campaign_fso")
    @Expose
    private Integer idCampaignFso;
    @SerializedName("id_campaign")
    @Expose
    private Integer idCampaign;
    @SerializedName("id_language")
    @Expose
    private Integer idLanguage;
    @SerializedName("id_fso_type")
    @Expose
    private Integer idFsoType;
    @SerializedName("campaignFsoValues")
    @Expose
    private List<CampaignFsoValue> campaignFsoValues = null;

    public Integer getIdCampaignFso() {
        return idCampaignFso;
    }

    public void setIdCampaignFso(Integer idCampaignFso) {
        this.idCampaignFso = idCampaignFso;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Integer getIdLanguage() {
        return idLanguage;
    }

    public void setIdLanguage(Integer idLanguage) {
        this.idLanguage = idLanguage;
    }

    public Integer getIdFsoType() {
        return idFsoType;
    }

    public void setIdFsoType(Integer idFsoType) {
        this.idFsoType = idFsoType;
    }

    public List<CampaignFsoValue> getCampaignFsoValues() {
        return campaignFsoValues;
    }

    public void setCampaignFsoValues(List<CampaignFsoValue> campaignFsoValues) {
        this.campaignFsoValues = campaignFsoValues;
    }

}