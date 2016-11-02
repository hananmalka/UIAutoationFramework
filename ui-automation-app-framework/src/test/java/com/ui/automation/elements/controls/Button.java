package com.ui.automation.elements.controls;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

/**
 * Created by Dana Shalev on 19/11/2015.
 */
public class Button extends BaseElement {

    public Button(Locator locator, Element parent) {
        super(locator, parent);
    }

    private static final String DECENDENT_BUTTON_XPATH = ".//button";

    public Button click() {
        Button btnToClick = getButtonElement();
        getDriver().actions().click(btnToClick);
        return btnToClick;
    }

    @Override
    public void expectThisElementToBeEnabled(boolean enabled){
        Button btnForEnabled = getButtonElement();
        if (enabled) {
            getDriver().expects().elementToBeEnabled(btnForEnabled);
        } else {
            getDriver().expects().elementToBeDisabled(btnForEnabled);
        }
    }

    @Override
    public boolean isThisElementEnabled(){
        Button btnForEnabled = getButtonElement();
        try {
            getDriver().expects().elementToBeEnabled(btnForEnabled);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public boolean isThisElementDisabled(){
        Button btnForEnabled = getButtonElement();
        try {
            getDriver().expects().elementToBeDisabled(btnForEnabled);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private Button getButtonElement() {
        Button btnForEnabled = this;
        String tag = this.getTagName();
        if (tag.equalsIgnoreCase("unknown")) {
            // for wait purpose, refactor when possible. then retry.
            btnForEnabled.isThisElementVisible();
            tag = this.getTagName();
            if (!tag.equalsIgnoreCase("button") && !tag.equalsIgnoreCase("unknown"))
                btnForEnabled = new Button(Locator.xpath(DECENDENT_BUTTON_XPATH), this);
        } else if (!tag.equalsIgnoreCase("button")) {
            btnForEnabled = new Button(Locator.xpath(DECENDENT_BUTTON_XPATH), this);
        }
        return btnForEnabled;
    }

    public void verifyIconClassToBe(String iconClass){
        new BaseElement(Locator.xpath(".//span[contains(@class, '" + iconClass + "')]"), this).expectVisible();
    }

}
