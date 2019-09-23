package com.ui.automation.pages.campaigns.newCampaginPage.elements.panelsContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.EngageNotificationSettingsPanel;

public class EngageNotificationSettingsGroup extends Form {

    public static EngageNotificationSettingsPanel engageNotificationSettingsPanel;

    public EngageNotificationSettingsGroup(BaseElement parent) {
        super(Locator.xpath("//div[contains(@class, 'component-content-wrapper')]//fieldset[4]"), parent);
        engageNotificationSettingsPanel = new EngageNotificationSettingsPanel(this);
    }
}
