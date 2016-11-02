package com.ui.automation.elements.controls;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

/**
 * User: noym
 * Date: 08/02/2016
 * Time: 23:01
 */
public class ToolbarIcon extends BaseElement {

    private static final String ICON_CLASS_XPATH = ".//button/div/span";

    public ToolbarIcon(Locator locator, Element parent) {
        super(locator, parent);
    }

    public void clickThisButton() {
        BaseElement button = new BaseElement(Locator.xpath(".//button"), this);
        getDriver().expects().elementToBeEnabled(button);
        button.clickOn();
    }

    public void verifyIconClassToBe(String iconClass){
        new BaseElement(Locator.xpath(ICON_CLASS_XPATH + "[contains(@class, '" + iconClass + "')]"), this).expectVisible();
    }

}
