package com.ui.automation.pages.campaigns.newCampaginPage.elements.panelsContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.entities.Campaign;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.AvailabilityPanel;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.MainPanel;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.TrackingPanel;

public class GeneralSettingsGroup extends Form {

    public MainPanel mainPanel;
    public AvailabilityPanel availabilityPanel;
    public TrackingPanel trackingPanel;

    public GeneralSettingsGroup(BaseElement parent)  {
        super(Locator.className("campaign-general-settings"), parent);
        mainPanel = new MainPanel(this);
        availabilityPanel = new AvailabilityPanel(this);
        trackingPanel = new TrackingPanel(this);
    }

    public void fillMainPanel(Campaign campaign) {
        mainPanel.fillPanel(campaign);
    }

    public void setGeneralSettings(Campaign campaign) {
//        mainPanel.advertiserDropDown().selectItem(campaign.getAdvertiserName());
//        mainPanel.titleDropDown().selectItem(campaign.getAdvertiserDescription());
//        mainPanel.bidTextBox().setValue(campaign.getBidRate());
//        mainPanel.costModelButton(campaign.getCostModel()).click();
    }

    public void setAvailabilityPane(Campaign campaign) {
//        for (Publisher publisher : campaign.getPublishers()) {
//            String publisherName = publisher.getInternalPublisherId();
//            availabilityPanel.publishersField().addNewTag(publisherName);
//        }

//        for (Geo country : campaign.getBids().getGeos()) {
//            availabilityPanel.countriesField().addNewTag(country.getName());
//        }
    }

    public void setTrackingPane(Campaign campaign) {

    }
}
