package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.dropdowns.AuraDropDown;
import com.ui.automation.enums.LimitationEnum;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.LimitationRowPanel;

public class BudgetGroupPanel extends Panel {

    static BudgetGroupSettingsPanel budgetGroupSettingsPane;
    static LimitationRowPanel limitationRowPane;

    public BudgetGroupPanel(BaseElement parent) {
        super(Locator.className("budgetGroup"), parent);
        budgetGroupSettingsPane = new BudgetGroupSettingsPanel(this);
    }

    public LimitationRowPanel limitationRowPane(LimitationEnum limitationEnum) {
        return new LimitationRowPanel(limitationEnum, this);
    }

    public AuraDropDown addLimitationDropDown() {
        return auraDropDownField();
    }
}
