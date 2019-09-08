package com.ui.automation.pages.campaigns.campaignsPage;

import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.campaignsPage.elements.CampaignsContainer;
import com.ui.automation.pages.campaigns.campaignsPage.elements.CampaignsPageHeader;

public class CampaignsPage extends BaseTopLevelElement {

    public CampaignsPageHeader campaignsPageHeader;
    public CampaignsContainer campaignsContainer;

    public CampaignsPage() {
        super(Locator.className("CampaignsComponent"));
        campaignsContainer = new CampaignsContainer(this);
        campaignsPageHeader = new CampaignsPageHeader(this);
    }

    /**
     * ATOMIC ACTIONS
     */

    public void clickCreateCampaignButton(){
        campaignsPageHeader.clickCreateCampaignButton();
    }
}


