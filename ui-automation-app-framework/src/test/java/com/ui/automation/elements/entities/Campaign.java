package com.ui.automation.elements.entities;

import com.ui.automation.pages.campaigns.newCampaginPage.NewCampaignPage;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.MainPanel;

import javax.annotation.Generated;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Generated("com.robohorse.robopojogenerator")
public class Campaign {

    public CampaignFsos campaignFsos;

    public Object engagementEvent;

    public Object externalId;

    public Capping capping;

    public Placements placements;

    public Object launchIntent;

    public Object installIntent;

    public Network network;

    public Object landingPageValidationUrl;

    public Targeting targeting;

    public List<Object> fsoApprovedPublishers;

    public Object idCampaign;

    public String impressionUrl;

    public String advertiser = "Upopa Games";

    public String title = "Hopeless: The Dark Cave";

    public String costModel;

    public RankingFactors rankingFactors;

    public List<Object> notificationApprovedPublishers;

    public Bids bids;

    public List<PublishersItem> publishers;

    public String campaignName;

    public Notifications notifications;

    public String installUrl;

    public void setCampaignFsos(CampaignFsos campaignFsos) {
        this.campaignFsos = campaignFsos;
    }

    public CampaignFsos getCampaignFsos() {
        return campaignFsos;
    }

    public void setEngagementEvent(Object engagementEvent) {
        this.engagementEvent = engagementEvent;
    }

    public Object getEngagementEvent() {
        return engagementEvent;
    }

    public void setExternalId(Object externalId) {
        this.externalId = externalId;
    }

    public Object getExternalId() {
        return externalId;
    }

    public void setCapping(Capping capping) {
        this.capping = capping;
    }

    public Capping getCapping() {
        return capping;
    }

    public void setPlacements(Placements placements) {
        this.placements = placements;
    }

    public Placements getPlacements() {
        return placements;
    }

    public void setLaunchIntent(Object launchIntent) {
        this.launchIntent = launchIntent;
    }

    public Object getLaunchIntent() {
        return launchIntent;
    }

    public void setInstallIntent(Object installIntent) {
        this.installIntent = installIntent;
    }

    public Object getInstallIntent() {
        return installIntent;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    public Network getNetwork() {
        return network;
    }

    public void setLandingPageValidationUrl(Object landingPageValidationUrl) {
        this.landingPageValidationUrl = landingPageValidationUrl;
    }

    public Object getLandingPageValidationUrl() {
        return landingPageValidationUrl;
    }

    public void setTargeting(Targeting targeting) {
        this.targeting = targeting;
    }

    public Targeting getTargeting() {
        return targeting;
    }

    public void setFsoApprovedPublishers(List<Object> fsoApprovedPublishers) {
        this.fsoApprovedPublishers = fsoApprovedPublishers;
    }

    public List<Object> getFsoApprovedPublishers() {
        return fsoApprovedPublishers;
    }

    public void setIdCampaign(Object idCampaign) {
        this.idCampaign = idCampaign;
    }

    public Object getIdCampaign() {
        return idCampaign;
    }

    public void setImpressionUrl(String impressionUrl) {
        this.impressionUrl = impressionUrl;
    }

    public String getImpressionUrl() {
        return impressionUrl;
    }

    public void setAdvertiserTitles(String advertiser) {
        this.advertiser = advertiser;
    }

    public String getAdvertiser() {
        return this.advertiser;
    }

    public void setCostModel(String costModel) {
        this.costModel = costModel;
    }

    public String getCostModel() {
        return costModel;
    }

    public void setRankingFactors(RankingFactors rankingFactors) {
        this.rankingFactors = rankingFactors;
    }

    public RankingFactors getRankingFactors() {
        return rankingFactors;
    }

    public void setNotificationApprovedPublishers(List<Object> notificationApprovedPublishers) {
        this.notificationApprovedPublishers = notificationApprovedPublishers;
    }

    public List<Object> getNotificationApprovedPublishers() {
        return notificationApprovedPublishers;
    }

    public void setBids(Bids bids) {
        this.bids = bids;
    }

    public Bids getBids() {
        return bids;
    }

    public void setPublishers(List<PublishersItem> publishers) {
        this.publishers = publishers;
    }

    public List<PublishersItem> getPublishers() {
        return publishers;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setNotifications(Notifications notifications) {
        this.notifications = notifications;
    }

    public Notifications getNotifications() {
        return notifications;
    }

    public void setInstallUrl(String installUrl) {
        this.installUrl = installUrl;
    }

    public String getInstallUrl() {
        return installUrl;
    }


//    @SerializedName("campaignFsos")
//    public CampaignFsos campaignFsos;
//
//    @SerializedName("engagementEvent")
//    public Object engagementEvent;
//
//    @SerializedName("externalId")
//    public Object externalId;
//
//    @SerializedName("capping")
//    public Capping capping;
//
//    @SerializedName("placements")
//    public Placements placements;
//
//    @SerializedName("launchIntent")
//    public Object launchIntent;
//
//    @SerializedName("installIntent")
//    public Object installIntent;
//
//    @SerializedName("network")
//    public Network network;
//
//    @SerializedName("landingPageValidationUrl")
//    public Object landingPageValidationUrl;
//
//    @SerializedName("targeting")
//    public Targeting targeting;
//
//    @SerializedName("fsoApprovedPublishers")
//    public List<Object> fsoApprovedPublishers;
//
//    @SerializedName("idCampaign")
//    public Object idCampaign;
//
//    @SerializedName("impressionUrl")
//    public String impressionUrl;
//
//    @SerializedName("advertiserTitles")
//    public AdvertiserTitles advertiserTitles;
//
//    @SerializedName("costModel")
//    public String costModel;
//
//    @SerializedName("rankingFactors")
//    public RankingFactors rankingFactors;
//
//    @SerializedName("notificationApprovedPublishers")
//    public List<Object> notificationApprovedPublishers;
//
//    @SerializedName("bids")
//    public Bids bids;
//
//    @SerializedName("publishers")
//    public List<PublishersItem> publishers;
//
//    @SerializedName("campaignName")
//    public String campaignName;
//
//    @SerializedName("notifications")
//    public Notifications notifications;
//
//    @SerializedName("installUrl")
//    public String installUrl;
//
//    public void setCampaignFsos(CampaignFsos campaignFsos) {
//        this.campaignFsos = campaignFsos;
//    }
//
//    public CampaignFsos getCampaignFsos() {
//        return campaignFsos;
//    }
//
//    public void setEngagementEvent(Object engagementEvent) {
//        this.engagementEvent = engagementEvent;
//    }
//
//    public Object getEngagementEvent() {
//        return engagementEvent;
//    }
//
//    public void setExternalId(Object externalId) {
//        this.externalId = externalId;
//    }
//
//    public Object getExternalId() {
//        return externalId;
//    }
//
//    public void setCapping(Capping capping) {
//        this.capping = capping;
//    }
//
//    public Capping getCapping() {
//        return capping;
//    }
//
//    public void setPlacements(Placements placements) {
//        this.placements = placements;
//    }
//
//    public Placements getPlacements() {
//        return placements;
//    }
//
//    public void setLaunchIntent(Object launchIntent) {
//        this.launchIntent = launchIntent;
//    }
//
//    public Object getLaunchIntent() {
//        return launchIntent;
//    }
//
//    public void setInstallIntent(Object installIntent) {
//        this.installIntent = installIntent;
//    }
//
//    public Object getInstallIntent() {
//        return installIntent;
//    }
//
//    public void setNetwork(Network network) {
//        this.network = network;
//    }
//
//    public Network getNetwork() {
//        return network;
//    }
//
//    public void setLandingPageValidationUrl(Object landingPageValidationUrl) {
//        this.landingPageValidationUrl = landingPageValidationUrl;
//    }
//
//    public Object getLandingPageValidationUrl() {
//        return landingPageValidationUrl;
//    }
//
//    public void setTargeting(Targeting targeting) {
//        this.targeting = targeting;
//    }
//
//    public Targeting getTargeting() {
//        return targeting;
//    }
//
//    public void setFsoApprovedPublishers(List<Object> fsoApprovedPublishers) {
//        this.fsoApprovedPublishers = fsoApprovedPublishers;
//    }
//
//    public List<Object> getFsoApprovedPublishers() {
//        return fsoApprovedPublishers;
//    }
//
//    public void setIdCampaign(Object idCampaign) {
//        this.idCampaign = idCampaign;
//    }
//
//    public Object getIdCampaign() {
//        return idCampaign;
//    }
//
//    public void setImpressionUrl(String impressionUrl) {
//        this.impressionUrl = impressionUrl;
//    }
//
//    public String getImpressionUrl() {
//        return impressionUrl;
//    }
//
//    public void setAdvertiserTitles(AdvertiserTitles advertiserTitles) {
//        this.advertiserTitles = advertiserTitles;
//    }
//
//    public AdvertiserTitles getAdvertiserTitles() {
//        return this.advertiserTitles;
//    }
//
//    public void setCostModel(String costModel) {
//        this.costModel = costModel;
//    }
//
//    public String getCostModel() {
//        return costModel;
//    }
//
//    public void setRankingFactors(RankingFactors rankingFactors) {
//        this.rankingFactors = rankingFactors;
//    }
//
//    public RankingFactors getRankingFactors() {
//        return rankingFactors;
//    }
//
//    public void setNotificationApprovedPublishers(List<Object> notificationApprovedPublishers) {
//        this.notificationApprovedPublishers = notificationApprovedPublishers;
//    }
//
//    public List<Object> getNotificationApprovedPublishers() {
//        return notificationApprovedPublishers;
//    }
//
//    public void setBids(Bids bids) {
//        this.bids = bids;
//    }
//
//    public Bids getBids() {
//        return bids;
//    }
//
//    public void setPublishers(List<PublishersItem> publishers) {
//        this.publishers = publishers;
//    }
//
//    public List<PublishersItem> getPublishers() {
//        return publishers;
//    }
//
//    public void setCampaignName(String campaignName) {
//        this.campaignName = campaignName;
//    }
//
//    public String getCampaignName() {
//        return campaignName;
//    }
//
//    public void setNotifications(Notifications notifications) {
//        this.notifications = notifications;
//    }
//
//    public Notifications getNotifications() {
//        return notifications;
//    }
//
//    public void setInstallUrl(String installUrl) {
//        this.installUrl = installUrl;
//    }
//
//    public String getInstallUrl() {
//        return installUrl;
//    }


    public class CampaignWebFields {
        MainPanel mainPanel = new NewCampaignPage().newCampaignForm.generalSettingsGroup.mainPanel;

        public Map<Field, FieldWebObject> fieldToWebElementMap = new HashMap<>();

        public CampaignWebFields() {
            try {
                fieldToWebElementMap.put(Campaign.class.getDeclaredField("advertiser"), new FieldWebObject(mainPanel.advertiserDropDown(), mainPanel.getClass()));
                fieldToWebElementMap.put(Campaign.class.getDeclaredField("title"), new FieldWebObject(mainPanel.titleDropDown(), mainPanel.getClass()));
            } catch (NoSuchFieldException ex) {
            }
        }
    }

    public CampaignWebFields getCampaignWebFields() {
        return new CampaignWebFields();
    }
}