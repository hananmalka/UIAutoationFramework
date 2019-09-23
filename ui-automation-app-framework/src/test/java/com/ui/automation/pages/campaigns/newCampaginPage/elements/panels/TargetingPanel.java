package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.dropdowns.StaticDropDown;
import com.ui.automation.locator.Locator;

public class TargetingPanel extends Panel {

    public TargetingPanel(Locator locator, BaseElement parent) {
        super(locator, parent);
    }

    public StaticDropDown addTargetingDropDown() {
        return staticDropDown();
    }
}
