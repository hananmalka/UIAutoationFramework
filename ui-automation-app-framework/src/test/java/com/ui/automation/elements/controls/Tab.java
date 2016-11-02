package com.ui.automation.elements.controls;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.base.BaseTopLevelElement;
import com.ui.automation.locator.Locator;


/**
 * Created by mendelan on 2/24/2016.
 */
public class Tab extends BaseElement{

    private static final String TITLE_ELEMENT_XPATH = ".//*[string-length(text()) > 0]";
    private static final String TAB_SELECTABLE_ELEMENT_CLASS = "selenium_tabTitle";
    private static final String TAB_DIRTY_SIGN_XPATH = ".//span[2]";
    private static final String TAB_DIRTY_SIGN_CHARACTER = "*";
    private static final String TAB_X_BTN_CLASS = "icon-close";

    private BaseElement errorIcon;
    private BaseElement errorIconText;
    private ToolbarIcon xButton = new ToolbarIcon(Locator.className(TAB_X_BTN_CLASS), this);

    public Tab(Locator locator, Element parent) {
        super(locator, parent);
    }

    public Tab(Locator locator, Element parent, Locator errorIcon) {
        super(locator, parent);
        this.errorIcon = new BaseElement(errorIcon, this);
    }

    public Tab(Locator locator, Element parent, Locator errorIcon, Locator errorIconText) {
        this(locator, parent, errorIcon);
        this.errorIconText = new BaseElement(errorIconText, this);
    }

    public String getTabName() {
        return new BaseElement(Locator.xpath(TITLE_ELEMENT_XPATH), this).getText();
    }

    public void selectTab(){
        new BaseElement(Locator.className(TAB_SELECTABLE_ELEMENT_CLASS), this).clickOn();
    }

    public BaseElement dirtyIcon() {
        return new BaseElement(Locator.xpath(TAB_DIRTY_SIGN_XPATH), this);
    }

    public boolean isTabDirty() {
        try {
            dirtyIcon().expectVisible();
        } catch (Exception e){
            return false;
        }
        return dirtyIcon().getText().equals(TAB_DIRTY_SIGN_CHARACTER);
    }

    public ToolbarIcon xButton() { return xButton; }
    public BaseElement errorIcon() { return errorIcon; }
    public BaseElement errorIconText() { return errorIconText; }
    public BaseElement getValidationIconByClassName(String className) {
        return new BaseElement(Locator.className(className), this);
    }

    public boolean verifyErrorMessage(String expectedErrorMessage) {
        errorIcon().clickOn();
        int index = 0;
        BaseTopLevelElement stepError = new BaseTopLevelElement(Locator.id("undefined_item" + index));
        while (true) {
            if (!stepError.isThisElementVisible()) {
                errorIcon().clickOn();
                return false;
            }
            if (stepError.getText().equals(expectedErrorMessage)) {
                errorIcon().clickOn();
                return true;
            } else stepError = new BaseTopLevelElement(Locator.id("undefined_item" + ++index));
        }
    }
}
