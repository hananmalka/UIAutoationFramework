package com.ui.automation.elements.filter;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.dropdowns.ReactSelectDropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

public class Filter extends BaseElement {

    public Filter(Element parent) {
        super(Locator.className("react-bs-table-tool-bar"), parent);
    }

    public TextBox searchBox(Locator locator) {
        return new TextBox(locator, this);
    }

    public ReactSelectDropDown dropDownFilter(Locator locator) {
        return new ReactSelectDropDown(locator, this);
    }
}
