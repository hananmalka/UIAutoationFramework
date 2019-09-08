package com.ui.automation.elements.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Capping {

    @SerializedName("enabled")
    @Expose
    private Boolean enabled;
    @SerializedName("automatically_managed")
    @Expose
    private Boolean automaticallyManaged;
    @SerializedName("advertiser_id")
    @Expose
    private Integer advertiserId;
    @SerializedName("capping_rules")
    @Expose
    private List<Object> cappingRules = null;
//    @SerializedName("alerts")
//    @Expose
//    private Alerts alerts;
    @SerializedName("email_list")
    @Expose
    private List<Object> emailList = null;
    @SerializedName("source")
    @Expose
    private String source;
//    @SerializedName("additional_data")
//    @Expose
//    private AdditionalData additionalData;
    @SerializedName("capping_groups")
    @Expose
    private List<Object> cappingGroups = null;
    @SerializedName("block_daily_limitation")
    @Expose
    private Boolean blockDailyLimitation;
    @SerializedName("block_monthly_total_limitation")
    @Expose
    private Boolean blockMonthlyTotalLimitation;

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getAutomaticallyManaged() {
        return automaticallyManaged;
    }

    public void setAutomaticallyManaged(Boolean automaticallyManaged) {
        this.automaticallyManaged = automaticallyManaged;
    }

    public Integer getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(Integer advertiserId) {
        this.advertiserId = advertiserId;
    }

    public List<Object> getCappingRules() {
        return cappingRules;
    }

    public void setCappingRules(List<Object> cappingRules) {
        this.cappingRules = cappingRules;
    }

//    public Alerts getAlerts() {
//        return alerts;
//    }
//
//    public void setAlerts(Alerts alerts) {
//        this.alerts = alerts;
//    }

    public List<Object> getEmailList() {
        return emailList;
    }

    public void setEmailList(List<Object> emailList) {
        this.emailList = emailList;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

//    public AdditionalData getAdditionalData() {
//        return additionalData;
//    }
//
//    public void setAdditionalData(AdditionalData additionalData) {
//        this.additionalData = additionalData;
//    }

    public List<Object> getCappingGroups() {
        return cappingGroups;
    }

    public void setCappingGroups(List<Object> cappingGroups) {
        this.cappingGroups = cappingGroups;
    }

    public Boolean getBlockDailyLimitation() {
        return blockDailyLimitation;
    }

    public void setBlockDailyLimitation(Boolean blockDailyLimitation) {
        this.blockDailyLimitation = blockDailyLimitation;
    }

    public Boolean getBlockMonthlyTotalLimitation() {
        return blockMonthlyTotalLimitation;
    }

    public void setBlockMonthlyTotalLimitation(Boolean blockMonthlyTotalLimitation) {
        this.blockMonthlyTotalLimitation = blockMonthlyTotalLimitation;
    }

}