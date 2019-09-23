package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.AutoCompleteTag;
import com.ui.automation.locator.Locator;

public class AvailabilityPanel extends Form {

    public AvailabilityPanel(BaseElement parent) {
        super(Locator.xpath("//*[contains(@class, 'campaign-general-settings')]/div[3]"), parent);
    }

    public AutoCompleteTag publishersField() {
        return new AutoCompleteTag(Locator.xpath("//*[contains(@class, 'publishers')]//*[@class='autoCompleteTagComponent']"), this);
    }

    public AutoCompleteTag countriesField() {
        return new AutoCompleteTag(Locator.xpath("//*[contains(@class, 'countries')]//*[@class='autoCompleteTagComponent']"), this);
    }
}
