package com.ui.automation.pages.campaigns.newCampaginPage;

import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.elements.header.Header;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.campaignsPage.elements.CampaignsPageHeader;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers.NewCampaignForm;

public class NewCampaignPage extends BaseTopLevelElement {

    public Header newCampaignPageHeader;
    public NewCampaignForm newCampaignForm;

    public NewCampaignPage() {
        super(Locator.className("SingleCampaignComponent"));
        newCampaignPageHeader = new CampaignsPageHeader(this);
        newCampaignForm = new NewCampaignForm(this);
    }

    /**
     * ATOMIC ACTIONS
     */


    /**
     * COMPLEX ACTIONS
     */
}