package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.NewAdvertiserPageHeader;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.MailingListPanel;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.MainPanel;

public class NewAdvertiserForm extends BaseElement {

    public NewAdvertiserPageHeader newAdvertiserPageHeader;
    public MainPanel generalSettingsPane;
    public MailingListPanel mailingListPane;
    public TitlesGroup titlesGroup;
    public BudgetAndTimeLimitationGroup budgetAndTimeLimitationGroup;
    public BudgetGroup budgetGroups;

    public NewAdvertiserForm(Element parent) {
        super(Locator.className("component-content"), parent);
        newAdvertiserPageHeader = new NewAdvertiserPageHeader(this);
        generalSettingsPane = new MainPanel(this);
        mailingListPane = new MailingListPanel(this);
        titlesGroup = new TitlesGroup(this);
        budgetAndTimeLimitationGroup = new BudgetAndTimeLimitationGroup(this);
        budgetGroups = new BudgetGroup(this);
    }
}

