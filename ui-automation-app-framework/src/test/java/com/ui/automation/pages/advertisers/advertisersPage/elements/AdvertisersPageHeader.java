package com.ui.automation.pages.advertisers.advertisersPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.header.Header;
import com.ui.automation.locator.Locator;

public class AdvertisersPageHeader extends Header {

    public AdvertisersPageHeader(Element parent) {
        super(parent);
    }

    public void clickAddAdvertiserButton() {
        headerButton(Locator.className("advertisers-buttons")).click();
    }
}
