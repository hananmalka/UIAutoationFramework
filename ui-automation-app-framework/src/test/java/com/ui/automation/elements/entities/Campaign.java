package com.ui.automation.elements.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Campaign extends Entity {

    @SerializedName("campaignsSegments")
    @Expose
    private List<Object> campaignsSegments = null;
    @SerializedName("idCampaign")
    @Expose
    private Integer idCampaign;
    @SerializedName("costModel")
    @Expose
    private String costModel;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("externalId")
    @Expose
    private Integer externalId;
    @SerializedName("packageName")
    @Expose
    private String packageName;
    @SerializedName("publishers")
    @Expose
    private List<Publisher> publishers = null;
    @SerializedName("impression_url")
    @Expose
    private String impressionUrl;
    @SerializedName("install_url")
    @Expose
    private String installUrl;
    @SerializedName("landing_page_validation_url")
    @Expose
    private String landingPageValidationUrl;
    @SerializedName("saveDate")
    @Expose
    private String saveDate;
    @SerializedName("creationDate")
    @Expose
    private String creationDate;
    @SerializedName("network")
    @Expose
    private Network network;
    @SerializedName("bids")
    @Expose
    private Bids bids;
    @SerializedName("targeting")
    @Expose
    private Targeting targeting;
    @SerializedName("capping")
    @Expose
    private Capping capping;
    @SerializedName("placements")
    @Expose
    private List<Placement> placements;
    @SerializedName("rankingFactors")
    @Expose
    private List<RankingFactor> rankingFactors;
    @SerializedName("campaignFsos")
    @Expose
    private List<CampaignFso> campaignFsos;
    @SerializedName("advertiserTitles")
    @Expose
    private AdvertiserTitles advertiserTitles;
    @SerializedName("engagementEvent")
    @Expose
    private Integer engagementEvent;
    @SerializedName("fsoApprovedPublishers")
    @Expose
    private List<Object> fsoApprovedPublishers = null;
    @SerializedName("notificationsApprovedPublishers")
    @Expose
    private List<Object> notificationsApprovedPublishers = null;
    @SerializedName("install_intent")
    @Expose
    private String installIntent;
    @SerializedName("launch_intent")
    @Expose
    private String launchIntent;
    @SerializedName("campaignNotifications")
    @Expose
    private List<CampaignNotification> campaignNotifications = null;

    public List<Object> getCampaignsSegments() {
        return campaignsSegments;
    }

    public void setCampaignsSegments(List<Object> campaignsSegments) {
        this.campaignsSegments = campaignsSegments;
    }

    public Integer getIdCampaign() {
        return idCampaign;
    }

    public void setIdCampaign(Integer idCampaign) {
        this.idCampaign = idCampaign;
    }

    public String getCostModel() {
        return costModel;
    }

    public void setCostModel(String costModel) {
        this.costModel = costModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getExternalId() {
        return externalId;
    }

    public void setExternalId(Integer externalId) {
        this.externalId = externalId;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public List<Publisher> getPublishers() {
        return publishers;
    }

    public void setPublishers(List<Publisher> publishers) {
        this.publishers = publishers;
    }

    public String getImpressionUrl() {
        return impressionUrl;
    }

    public void setImpressionUrl(String impressionUrl) {
        this.impressionUrl = impressionUrl;
    }

    public String getInstallUrl() {
        return installUrl;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getLandingPageValidationUrl() {
        return landingPageValidationUrl;
    }

    public void setLandingPageValidationUrl(String landingPageValidationUrl) {
        this.landingPageValidationUrl = landingPageValidationUrl;
    }

    public String getSaveDate() {
        return saveDate;
    }

    public void setSaveDate(String saveDate) {
        this.saveDate = saveDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Bids getBids() {
        return bids;
    }

    public void setBids(Bids bids) {
        this.bids = bids;
    }

    public Targeting getTargeting() {
        return targeting;
    }

    public void setTargeting(Targeting targeting) {
        this.targeting = targeting;
    }

    public Capping getCapping() {
        return capping;
    }

    public void setCapping(Capping capping) {
        this.capping = capping;
    }

    public List<Placement> getPlacements() {
        return placements;
    }

    public void setPlacements(List<Placement> placements) {
        this.placements = placements;
    }

    public List<RankingFactor> getRankingFactors() {
        return rankingFactors;
    }

    public void setRankingFactors(List<RankingFactor> rankingFactors) {
        this.rankingFactors = rankingFactors;
    }

    public List<CampaignFso> getCampaignFsos() {
        return campaignFsos;
    }

    public void setCampaignFsos(List<CampaignFso> campaignFsos) {
        this.campaignFsos = campaignFsos;
    }

    public AdvertiserTitles getAdvertiserTitles() {
        return advertiserTitles;
    }

    public void setAdvertiserTitles(AdvertiserTitles advertiserTitles) {
        this.advertiserTitles = advertiserTitles;
    }

    public Integer getEngagementEvent() {
        return engagementEvent;
    }

    public void setEngagementEvent(Integer engagementEvent) {
        this.engagementEvent = engagementEvent;
    }

    public List<Object> getFsoApprovedPublishers() {
        return fsoApprovedPublishers;
    }

    public void setFsoApprovedPublishers(List<Object> fsoApprovedPublishers) {
        this.fsoApprovedPublishers = fsoApprovedPublishers;
    }

    public List<Object> getNotificationsApprovedPublishers() {
        return notificationsApprovedPublishers;
    }

    public void setNotificationsApprovedPublishers(List<Object> notificationsApprovedPublishers) {
        this.notificationsApprovedPublishers = notificationsApprovedPublishers;
    }

    public String getInstallIntent() {
        return installIntent;
    }

    public void setInstallIntent(String installIntent) {
        this.installIntent = installIntent;
    }

    public String getLaunchIntent() {
        return launchIntent;
    }

    public void setLaunchIntent(String launchIntent) {
        this.launchIntent = launchIntent;
    }

    public List<CampaignNotification> getCampaignNotifications() {
        return campaignNotifications;
    }

    public void setCampaignNotifications(List<CampaignNotification> campaignNotifications) {
        this.campaignNotifications = campaignNotifications;
    }

}