package com.ui.automation.elements.componentcontent;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.table.Table;
import com.ui.automation.locator.Locator;

public abstract class ComponentContent extends BaseElement {

    public ComponentContent(Locator locator, Element parent) {
        super(locator, parent);
    }

    public Table table() {
        return new Table(this);
    }
}
