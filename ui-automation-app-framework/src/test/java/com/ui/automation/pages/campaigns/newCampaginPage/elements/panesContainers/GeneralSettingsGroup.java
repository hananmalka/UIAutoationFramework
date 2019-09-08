package com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.entities.Campaign;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.AvailabilityPane;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.MainPane;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.TrackingPane;

public class GeneralSettingsGroup extends Form {

    static MainPane mainPane;
    static AvailabilityPane availabilityPane;
    static TrackingPane trackingPane;

    public GeneralSettingsGroup(BaseElement parent) {
        super(Locator.className("campaign-general-settings"), parent);
        mainPane = new MainPane(this);
        availabilityPane = new AvailabilityPane(this);
        trackingPane = new TrackingPane(this);
    }

    public void setGeneralSettings(Campaign campaign) {
        mainPane.fillAll(campaign);
    }
}
