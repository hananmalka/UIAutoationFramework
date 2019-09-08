package com.ui.automation.elements.controls.dropdowns;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

public class ReactSelectDropDown extends BaseElement {

    public ReactSelectDropDown(Locator locator, Element parent) {
        super(locator, parent);
    }

    public ReactSelectDropDown(String placeholder, Element parent) {
        super(Locator.xpath("//div[text()='" + placeholder + "]//ancestor::*[contains(@class, 'react-select__control')]//parent::*[contains(@class, 'filter')]"), parent);
    }

    public void open() {
        this.clickOn();
    }

    public void selectItem(String listItem) {
        open();
        selectItemFromDropDown(listItem);
    }

    private void selectItemFromDropDown(String listItem) {
        BaseElement dropDownListItem = new BaseElement(Locator.xpath("//div[text()='" + listItem + "']"), this);
        getDriver().actions().hoverAndClick(dropDownListItem, dropDownListItem);
    }
}
