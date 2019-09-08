package com.ui.automation.elements.controls.dropdowns;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

public class AutoCompleteDropDown extends BaseElement {

    public AutoCompleteDropDown(Element parent) {
        super(Locator.css("ul[role='listbox']"), parent);
    }

    public void selectItem(String item) {
        BaseElement listItem = new BaseElement(Locator.xpath("//li//span[text()='" + item + "']"), this);
        listItem.clickOn();
    }
}
