package com.ui.automation.elements.controls.dropdowns;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

public class AuraDropDown extends BaseElement {

    private static final String NESTED_LIST_ITEM_XPATH_TEMPLATE = "//div[text()='<group>']//following-sibling::div//div[text()='<list_item_value>']";
    private static final String LIST_ITEM_XPATH_TEMPLATE = "div[text()='<list_item_value>']";

    public AuraDropDown(Element parent) {
        super(Locator.id("aura-dd"), parent);
    }

    public AuraDropDown(Locator locator, Element parent) {
        super(locator, parent);
    }

    public void open() {
        this.clickOn();
    }

    public void selectItem(String item) {
        BaseElement listItem = getItemFromList(item);
        getDriver().actions().click(listItem);
    }

    public void selectItemFromNestedList(String group, String item) {
        BaseElement listItem = getItemFromNestedList(group, item);
        getDriver().actions().click(listItem);
    }

    private BaseElement getItemFromList(String item) {
        String itemLocator = LIST_ITEM_XPATH_TEMPLATE.replace("<list_item_value>", item);
        return new BaseElement(Locator.xpath(itemLocator), this);
    }

    private BaseElement getItemFromNestedList(String group, String item) {
        String itemLocator = NESTED_LIST_ITEM_XPATH_TEMPLATE.replace("<group>", group).replace("<list_item_value>", item);
        return new BaseElement(Locator.xpath(itemLocator), this);
    }
}
