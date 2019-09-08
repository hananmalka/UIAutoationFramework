package com.ui.automation.pages.campaigns.campaignsPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.header.Header;
import com.ui.automation.locator.Locator;

public class CampaignsPageHeader extends Header {

    public CampaignsPageHeader(Element parent) {
        super(parent);
    }

    public void clickCreateCampaignButton() {
        headerButton(Locator.id("campaigns-list-create")).click();
    }
}
