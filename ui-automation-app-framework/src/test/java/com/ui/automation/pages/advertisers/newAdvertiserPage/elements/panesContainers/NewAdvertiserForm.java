package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.componentcontent.ComponentContent;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.NewAdvertiserPageHeader;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.MailingListPane;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.MainPane;

public class NewAdvertiserForm extends BaseElement {

    public NewAdvertiserPageHeader newAdvertiserPageHeader;
    public MainPane generalSettingsPane;
    public MailingListPane mailingListPane;
    public TitlesGroup titlesGroup;
    public BudgetAndTimeLimitationGroup budgetAndTimeLimitationGroup;
    public BudgetGroup budgetGroups;

    public NewAdvertiserForm(Element parent) {
        super(Locator.className("component-content"), parent);
        newAdvertiserPageHeader = new NewAdvertiserPageHeader(this);
        generalSettingsPane = new MainPane(this);
        mailingListPane = new MailingListPane(this);
        titlesGroup = new TitlesGroup(this);
        budgetAndTimeLimitationGroup = new BudgetAndTimeLimitationGroup(this);
        budgetGroups = new BudgetGroup(this);
    }
}

