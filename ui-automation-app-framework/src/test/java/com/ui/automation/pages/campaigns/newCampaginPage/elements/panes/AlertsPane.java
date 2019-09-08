package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.CheckBox;
import com.ui.automation.locator.Locator;

public class AlertsPane extends Pane {

    public AlertsPane(BaseElement parent) {
        super(Locator.className("limitation-alerts"), parent);
    }

    public CheckBox alertCheckbox() {
        return checkBoxField();
    }

    public BaseElement trashIcon() {
        return new BaseElement(Locator.className("iconmoon-bin"), this);
    }
}
