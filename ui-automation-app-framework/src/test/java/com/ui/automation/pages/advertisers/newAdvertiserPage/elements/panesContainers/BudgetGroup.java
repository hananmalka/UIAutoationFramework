package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.BudgetGroupPane;

public class BudgetGroup extends Form {

    static BudgetGroupPane budgetGroupPane;

    public BudgetGroup(BaseElement parent) {
        super(Locator.className("budgetGroupSection"), parent);
        budgetGroupPane = new BudgetGroupPane(this);
    }

    public Button addGroupButton() {
        return new Button(Locator.id("budget-group-add-group"), this);
    }
}
