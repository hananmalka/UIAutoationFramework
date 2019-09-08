package com.ui.automation.pages.advertisers.advertisersPage;

import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.advertisersPage.elements.AdvertisersContainer;
import com.ui.automation.pages.advertisers.advertisersPage.elements.AdvertisersPageHeader;

public class AdvertisersPage extends BaseTopLevelElement {

    public AdvertisersContainer advertisersContainer;
    public AdvertisersPageHeader advertisersPageHeader;

    public AdvertisersPage() {
        super(Locator.className("advertisers-container"));
        advertisersContainer = new AdvertisersContainer(this);
        advertisersPageHeader = new AdvertisersPageHeader(this);
    }
}
