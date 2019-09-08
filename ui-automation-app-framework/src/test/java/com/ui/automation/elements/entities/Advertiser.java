package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Advertiser {

    @SerializedName("id_advertiser")
    @Expose
    private Integer idAdvertiser;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("owner")
    @Expose
    private Integer owner;
    @SerializedName("salesforce_id")
    @Expose
    private String salesforceId;
    @SerializedName("id_status")
    @Expose
    private Integer idStatus;
    @SerializedName("id_sf_advertiser")
    @Expose
    private String idSfAdvertiser;
    @SerializedName("is_rev_share")
    @Expose
    private Boolean isRevShare;
    @SerializedName("support_install_intent")
    @Expose
    private Object supportInstallIntent;
    @SerializedName("support_launch_intent")
    @Expose
    private Object supportLaunchIntent;
    @SerializedName("token")
    @Expose
    private Object token;

    public Integer getIdAdvertiser() {
        return idAdvertiser;
    }

    public void setIdAdvertiser(Integer idAdvertiser) {
        this.idAdvertiser = idAdvertiser;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOwner() {
        return owner;
    }

    public void setOwner(Integer owner) {
        this.owner = owner;
    }

    public String getSalesforceId() {
        return salesforceId;
    }

    public void setSalesforceId(String salesforceId) {
        this.salesforceId = salesforceId;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.idStatus = idStatus;
    }

    public String getIdSfAdvertiser() {
        return idSfAdvertiser;
    }

    public void setIdSfAdvertiser(String idSfAdvertiser) {
        this.idSfAdvertiser = idSfAdvertiser;
    }

    public Boolean getIsRevShare() {
        return isRevShare;
    }

    public void setIsRevShare(Boolean isRevShare) {
        this.isRevShare = isRevShare;
    }

    public Object getSupportInstallIntent() {
        return supportInstallIntent;
    }

    public void setSupportInstallIntent(Object supportInstallIntent) {
        this.supportInstallIntent = supportInstallIntent;
    }

    public Object getSupportLaunchIntent() {
        return supportLaunchIntent;
    }

    public void setSupportLaunchIntent(Object supportLaunchIntent) {
        this.supportLaunchIntent = supportLaunchIntent;
    }

    public Object getToken() {
        return token;
    }

    public void setToken(Object token) {
        this.token = token;
    }
}