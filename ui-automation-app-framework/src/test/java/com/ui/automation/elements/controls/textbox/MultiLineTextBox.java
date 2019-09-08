package com.ui.automation.elements.controls.textbox;

import com.ui.automation.elements.api.Element;
import com.ui.automation.locator.Locator;

public class MultiLineTextBox extends TextBoxAbstract {

    public MultiLineTextBox(String label, Element parent) {
        super(Locator.xpath("//label[text()='" + label + "']//parent::div//following-sibling::div//textarea"), parent);
    }
}
