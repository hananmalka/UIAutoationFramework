package com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.EngageNotificationSettingsPane;

public class EngageNotificationSettingsGroup extends Form {

    static EngageNotificationSettingsPane engageNotificationSettingsPane;

    public EngageNotificationSettingsGroup(BaseElement parent) {
        super(Locator.xpath("//div[contains(@class, 'component-content-wrapper')]//fieldset[4]"), parent);
        engageNotificationSettingsPane = new EngageNotificationSettingsPane(this);
    }
}
