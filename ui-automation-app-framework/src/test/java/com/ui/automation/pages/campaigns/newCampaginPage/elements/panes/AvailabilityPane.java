package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.locator.Locator;

public class AvailabilityPane extends Form {

    public AvailabilityPane(BaseElement parent) {
        super(Locator.xpath("//*[contains(@class, 'campaign-general-settings')]/div[3]"), parent);
    }
}
