package com.ui.automation.pages.campaigns.campaignsPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.componentcontent.ComponentContent;
import com.ui.automation.elements.controls.table.Table;
import com.ui.automation.locator.Locator;

public class CampaignsContainer extends ComponentContent {

    public CampaignsPageFilter campaignsPageFilter;
    public Table campaignPageTable;

    public CampaignsContainer(Element parent) {
        super(Locator.xpath("//*[contains(@class, 'CampaignsComponent')]//*[@class='component-content']"), parent);
        campaignsPageFilter = new CampaignsPageFilter(this);
        campaignPageTable =  this.table();
    }

    public CampaignsPageFilter campaignsPageFilter() {
        return campaignsPageFilter;
    }
}
