package com.ui.automation.elements.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CampaignNotification {

    @SerializedName("id_campaign_notification")
    @Expose
    private Integer idCampaignNotification;
    @SerializedName("id_campaign")
    @Expose
    private Integer idCampaign;
    @SerializedName("icon_url")
    @Expose
    private String iconUrl;
    @SerializedName("banner_url")
    @Expose
    private String bannerUrl;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("collapsed_background_image_url")
    @Expose
    private String collapsedBackgroundImageUrl;
    @SerializedName("background_color")
    @Expose
    private String backgroundColor;
    @SerializedName("text_color")
    @Expose
    private String textColor;
    @SerializedName("expanded_background_image_url")
    @Expose
    private String expandedBackgroundImageUrl;
    @SerializedName("layout")
    @Expose
    private String layout;

    public Integer getIdCampaignNotification() {
        return idCampaignNotification;
    }

    public void setIdCampaignNotification(Integer idCampaignNotification) {
        this.idCampaignNotification = idCampaignNotification;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCollapsedBackgroundImageUrl() {
        return collapsedBackgroundImageUrl;
    }

    public void setCollapsedBackgroundImageUrl(String collapsedBackgroundImageUrl) {
        this.collapsedBackgroundImageUrl = collapsedBackgroundImageUrl;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getExpandedBackgroundImageUrl() {
        return expandedBackgroundImageUrl;
    }

    public void setExpandedBackgroundImageUrl(String expandedBackgroundImageUrl) {
        this.expandedBackgroundImageUrl = expandedBackgroundImageUrl;
    }

    public String getLayout() {
        return layout;
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

}