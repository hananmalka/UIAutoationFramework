package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.ToggleButton;
import com.ui.automation.elements.controls.dropdowns.AuraDropDown;
import com.ui.automation.enums.LimitationEnum;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.BudgetAndTimeLimitationTogglesPanel;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.LimitationRowPanel;

public class BudgetAndTimeLimitationGroup extends Form {

    static BudgetAndTimeLimitationTogglesPanel togglesLimitationPane;
    static ToggleButton budgetToggleAutoManager;

    public BudgetAndTimeLimitationGroup(BaseElement parent) {
        super(Locator.className("capping-section "), parent);
        togglesLimitationPane = new BudgetAndTimeLimitationTogglesPanel(this);
        budgetToggleAutoManager = toggleButton(Locator.id("budget-auto-manager-toggle"));
    }

    public LimitationRowPanel limitationRowPane(LimitationEnum limitationEnum) {
        return new LimitationRowPanel(limitationEnum, this);
    }

    public AuraDropDown addLimitationDropDown() {
        return auraDropDownField();
    }
}
