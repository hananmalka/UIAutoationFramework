package com.ui.automation.elements.controls.textbox;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

public abstract class TextBoxAbstract extends BaseElement {

    private static final String DATA_ERROR_TEXT_ATTRIBUTE_NAME = "error";

    public TextBoxAbstract(Locator locator, Element parent) {
        super(locator, parent);
    }

    public void clearInputFieldValueWithBackspace() {
        getDriver().expects().elementToBeEnabled(this);
        getDriver().actions().clickBackspaceNTimes(this, getDriver().finds().getText(this).length());
    }

    public void clearValue() {
        getDriver().actions().clearText(this);
    }

    public void clickBackspaceNTimes(int times) {
        getDriver().actions().clickBackspaceNTimes(this, times);
    }

    public void expectTextBoxErrorMessageToBe(Element parent, Locator dataErrorXpath, String expectedText) {
        BaseElement element = new BaseElement(dataErrorXpath, parent);
        getDriver().expects().elementToContainAttrValue(element, DATA_ERROR_TEXT_ATTRIBUTE_NAME, expectedText);
    }

    public void expectValueToBe(String expectedValue) {
        getDriver().expects().elementTextToBeEqual(this, expectedValue);
    }
}
