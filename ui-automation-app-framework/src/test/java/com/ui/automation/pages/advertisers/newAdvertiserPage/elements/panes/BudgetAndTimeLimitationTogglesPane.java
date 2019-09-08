package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.CheckBox;
import com.ui.automation.locator.Locator;

public class BudgetAndTimeLimitationTogglesPane extends Pane {

    public BudgetAndTimeLimitationTogglesPane(BaseElement parent) {
        super(Locator.xpath("//*[contains(@class,'capping-section')]//*[@class='panel-body']"), parent);
    }

    public CheckBox reachDailyLimitationsCheckbox() {
        return checkBoxField(Locator.id("daily-switch"));
    }

    public CheckBox reachMonthlyLimitationsCheckbox() {
        return checkBoxField(Locator.id("monthly-switch"));
    }
}
