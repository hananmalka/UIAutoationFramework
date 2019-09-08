package com.ui.automation.elements.controls.textbox;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;

/**
 * Created by Dana Shalev on 25/11/2015.
 * Edited by Moshe on 21/04/2016.
 */
public class TextBox extends TextBoxAbstract {

    private static final String DATA_ERROR_TEXT_ATTRIBUTE_NAME = "data-error-text";

    public TextBox(String label, Element parent) {
        super(Locator.xpath("//label[text()='" + label + "']//parent::div//following-sibling::div//input[@type='text']"), parent);
    }

    public TextBox(Locator locator, Element parent) {
        super(locator, parent);
    }
}
