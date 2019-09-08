package com.ui.automation.elements.controls;

import com.ui.automation.elements.api.Element;
import com.ui.automation.locator.Locator;

public class ToggleButton extends CheckBox {

    public ToggleButton(Element parent) {
        super(parent);
    }

    public ToggleButton(Locator locator, Element parent) {
        super(locator, parent);
    }
}
