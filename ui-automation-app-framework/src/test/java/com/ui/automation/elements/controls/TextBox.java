package com.ui.automation.elements.controls;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

/**
 * Created by Dana Shalev on 25/11/2015.
 * Edited by Moshe on 21/04/2016.
 */
public class TextBox extends BaseElement {

    private static final String DATA_ERROR_TEXT_ATTRIBUTE_NAME = "data-error-text";
    private static final String DATA_ERROR_TEXT_XPATH = ".//span[(@data-error-text) > 0]";//".//span[string-length(@data-error-text) > 0]";

    public TextBox(Element parent){
        super(Locator.xpath(".//input[@type='text']"), parent);
    }

    public TextBox(Locator locator, Element parent) {
        super(locator, parent);
    }

    public void clearInputFieldValueWithBackspace() {
        getDriver().expects().elementToBeEnabled(this);
        getDriver().actions().clickBackspaceNTimes(this, getDriver().finds().getText(this).length());
    }

    public TextBox clearValue() {
        getDriver().actions().clearText(this);
        return this;
    }

    public TextBox clickBackspaceNTimes(int times) {
        getDriver().actions().clickBackspaceNTimes(this, times);
        return this;
    }

    public void expectTextBoxErrorMessageToBe (Element parent, Locator dataErrorXpath, String expectedText) {
        BaseElement element = new BaseElement(dataErrorXpath, parent);
        getDriver().expects().elementToContainAttrValue(element, DATA_ERROR_TEXT_ATTRIBUTE_NAME, expectedText);
    }

    public TextBox expectValueToBe(String expectedValue) {
        getDriver().expects().elementTextToBeEqual(this, expectedValue);
        return this;
    }
}
