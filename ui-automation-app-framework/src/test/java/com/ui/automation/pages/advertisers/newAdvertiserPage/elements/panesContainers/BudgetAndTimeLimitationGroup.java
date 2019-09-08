package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.ToggleButton;
import com.ui.automation.elements.controls.dropdowns.AuraDropDown;
import com.ui.automation.enums.LimitationEnum;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.BudgetAndTimeLimitationTogglesPane;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.LimitationRowPane;

public class BudgetAndTimeLimitationGroup extends Form {

    static BudgetAndTimeLimitationTogglesPane togglesLimitationPane;
    static ToggleButton budgetToggleAutoManager;

    public BudgetAndTimeLimitationGroup(BaseElement parent) {
        super(Locator.className("capping-section "), parent);
        togglesLimitationPane = new BudgetAndTimeLimitationTogglesPane(this);
        budgetToggleAutoManager = toggleButton(Locator.id("budget-auto-manager-toggle"));
    }

    public LimitationRowPane limitationRowPane(LimitationEnum limitationEnum) {
        return new LimitationRowPane(limitationEnum, this);
    }

    public AuraDropDown addLimitationDropDown() {
        return auraDropDownField();
    }
}
