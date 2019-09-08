package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CampaignFsoValue {

    @SerializedName("id_campaign_fso_value")
    @Expose
    private Integer idCampaignFsoValue;
    @SerializedName("id_campaign_fso")
    @Expose
    private Integer idCampaignFso;
    @SerializedName("id_fso_metadata")
    @Expose
    private Integer idFsoMetadata;
    @SerializedName("value")
    @Expose
    private String value;

    public Integer getIdCampaignFsoValue() {
        return idCampaignFsoValue;
    }

    public void setIdCampaignFsoValue(Integer idCampaignFsoValue) {
        this.idCampaignFsoValue = idCampaignFsoValue;
    }

    public Integer getIdCampaignFso() {
        return idCampaignFso;
    }

    public void setIdCampaignFso(Integer idCampaignFso) {
        this.idCampaignFso = idCampaignFso;
    }

    public Integer getIdFsoMetadata() {
        return idFsoMetadata;
    }

    public void setIdFsoMetadata(Integer idFsoMetadata) {
        this.idFsoMetadata = idFsoMetadata;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}