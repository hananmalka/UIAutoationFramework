package com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.componentcontent.ComponentContent;
import com.ui.automation.elements.entities.Campaign;
import com.ui.automation.locator.Locator;

public class NewCampaignForm extends ComponentContent {

    public GeneralSettingsGroup generalSettingsGroup;
    public BudgetAndCappingGroup budgetAndCappingGroup;
    public RankingPriorityGroup rankingPriorityGroup;
    public FullScreenOfferGroup fullScreenOfferGroup;
    public EngageNotificationSettingsGroup engageNotificationSettingsGroup;

    public NewCampaignForm(Element parent) {
        super(Locator.className("component-content"), parent);
        generalSettingsGroup = new GeneralSettingsGroup(this);
        budgetAndCappingGroup = new BudgetAndCappingGroup(this);
        rankingPriorityGroup = new RankingPriorityGroup(this);
        fullScreenOfferGroup = new FullScreenOfferGroup(this);
        engageNotificationSettingsGroup = new EngageNotificationSettingsGroup(this);
    }

    public void fillGeneralSettings(Campaign campaign) {
        generalSettingsGroup.mainPane.fillAll(campaign);
    }
}
