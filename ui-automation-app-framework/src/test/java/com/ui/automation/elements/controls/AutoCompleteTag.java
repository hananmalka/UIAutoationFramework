package com.ui.automation.elements.controls;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.dropdowns.AutoCompleteDropDown;
import com.ui.automation.locator.Locator;

public class AutoCompleteTag extends BaseElement {

    public AutoCompleteDropDown autoCompleteDropDown;
    private BaseElement addTagButton;

    public AutoCompleteTag(Element parent) {
        this(Locator.className("autoCompleteTagComponent"), parent);
    }

    public AutoCompleteTag(Locator locator, Element parent) {
        super(locator, parent);
        addTagButton = new BaseElement(Locator.className("react-tagsinput-input"), this);
    }

    public void addNewTag(String tagValue) {
        openTagField();
        BaseElement tagElement = new BaseElement(Locator.xpath("//div[@id='react-autowhatever-1']//span[text()='" + tagValue + "']"), this);
        tagElement.clickOn();
    }

    public void openTagField() {
        addTagButton.doubleClick();
    }

    public void removeTag(String tagName) {

    }

}
