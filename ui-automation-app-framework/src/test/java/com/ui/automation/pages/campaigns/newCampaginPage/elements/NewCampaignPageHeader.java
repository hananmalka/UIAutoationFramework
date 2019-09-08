package com.ui.automation.pages.campaigns.newCampaginPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.header.Header;
import com.ui.automation.locator.Locator;

public class NewCampaignPageHeader extends Header {

    public NewCampaignPageHeader(Element parent) {
        super(parent);
    }

    public BaseElement headerMenuButton() {
        return new BaseElement(Locator.className("header-menu"), this);
    }
}
