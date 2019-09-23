package com.ui.automation.elements.controls.dropdowns;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.IControl;
import com.ui.automation.locator.Locator;

public class DropDown extends BaseElement implements IControl {

    private static final String LIST_ITEM_XPATH_TEMPLATE = "//option[contains(text(),'<list_item_value>')]";

    public DropDown(Element parent){
        super(Locator.css("select"), parent);
    }

    public DropDown(Locator locator, Element parent) {
        super(locator, parent);
    }

    public void open() {
        this.clickOn();
    }

    public void selectItem(String item) {
        open();
        BaseElement listItem = getItemFromList(item);
        getDriver().actions().hoverAndClick(listItem, listItem);
    }

    private BaseElement getItemFromList(String item) {
        return new BaseElement(Locator.xpath(LIST_ITEM_XPATH_TEMPLATE.replace("<list_item_value>", item)), this);
    }

    @Override
    public void setValue(Object value) {
        selectItem(value.toString());
    }
}
