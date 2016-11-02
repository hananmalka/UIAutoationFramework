package com.ui.automation.elements.controls;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

public class CheckBox extends BaseElement {

    public CheckBox(Element parent) {
        super(Locator.xpath(".//input[@type='checkbox']//.."), parent);
    }

    public CheckBox(Locator locator, Element parent) {
        super(locator, parent);
    }

    public CheckBox click() {
        getDriver().actions().click(this);
        return this;
    }

    public CheckBox set(boolean value) {
        getDriver().actions().setCheckbox(this, inputElement(), value);
        if (value) {
            getDriver().expects().elementToBeSelected(inputElement());
        } else {
            getDriver().expects().elementNotToBeSelected(inputElement());
        }
        return this;
    }

    private Element inputElement() {
        return new BaseElement(Locator.xpath(".//input[@type='checkbox']"), this);
    }

    public CheckBox expectChecked() {
        getDriver().expects().elementToBeSelected(inputElement());
        return this;
    }

    public CheckBox expectUnChecked() {
        getDriver().expects().elementNotToBeSelected(inputElement());
        return this;
    }

    public CheckBox expectDisabled() {
        getDriver().expects().elementToBeDisabled(inputElement());
        return this;
    }

    public CheckBox expectEnabled() {
        getDriver().expects().elementToBeEnabled(inputElement());
        return this;
    }

    public CheckBox expectLabelValueToBe(String expectedValue) {
        getDriver().expects().elementTextToBeEqual(this, expectedValue);
        return this;
    }

    public CheckBox expectLabelValueToContain(String expectedValue) {
        getDriver().expects().elementToContainText(this, expectedValue);
        return this;
    }
}
