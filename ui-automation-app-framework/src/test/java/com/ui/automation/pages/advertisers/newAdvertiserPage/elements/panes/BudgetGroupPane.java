package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.dropdowns.AuraDropDown;
import com.ui.automation.enums.LimitationEnum;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.LimitationRowPane;

public class BudgetGroupPane extends Pane {

    static BudgetGroupSettingsPane budgetGroupSettingsPane;
    static LimitationRowPane limitationRowPane;

    public BudgetGroupPane(BaseElement parent) {
        super(Locator.className("budgetGroup"), parent);
        budgetGroupSettingsPane = new BudgetGroupSettingsPane(this);
    }

    public LimitationRowPane limitationRowPane(LimitationEnum limitationEnum) {
        return new LimitationRowPane(limitationEnum, this);
    }

    public AuraDropDown addLimitationDropDown() {
        return auraDropDownField();
    }
}
