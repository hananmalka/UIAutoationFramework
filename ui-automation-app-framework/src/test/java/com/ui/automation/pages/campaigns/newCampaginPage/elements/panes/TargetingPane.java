package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.dropdowns.StaticDropDown;
import com.ui.automation.locator.Locator;

public class TargetingPane extends Pane {

    public TargetingPane(Locator locator, BaseElement parent) {
        super(locator, parent);
    }

    public StaticDropDown addTargetingDropDown() {
        return staticDropDown();
    }
}
