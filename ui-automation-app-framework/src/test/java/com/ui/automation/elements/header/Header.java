package com.ui.automation.elements.header;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;

public class Header extends BaseElement {

    public Header(Element parent) {
        super(Locator.className("page-title"), parent);
    }

    public Button headerButton(Locator locator) {
        return new Button(locator, this);
    }

    public String getHeaderTitle() {
        return this.getText();
    }

    public Button cancelButton() {
        return new Button(Locator.id("main-button-cancel"), this);
    }

    public String entityID() {
        BaseElement entityID = new BaseElement(Locator.className("item_id"), this);
        return entityID.getText();
    }

    public String entityTitle() {
        BaseElement entityID = new BaseElement(Locator.className("EditableText"), this);
        return entityID.getText();
    }
}
