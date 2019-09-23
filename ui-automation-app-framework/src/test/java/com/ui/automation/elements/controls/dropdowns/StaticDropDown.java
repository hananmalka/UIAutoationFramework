package com.ui.automation.elements.controls.dropdowns;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;

public class StaticDropDown extends BaseElement {

    public StaticDropDown(Element parent) {
        super(Locator.className("dropdown"), parent);
    }

    public void open(Locator locator) {
        Button dropDownButton = new Button(locator, this);
        dropDownButton.click();
    }

    public void selectItem(String item) {
        BaseElement listItem = new BaseElement(Locator.xpath("//li//a[contains(text(),'" + item + "')]"), this);
        listItem.clickOn();
    }
}
