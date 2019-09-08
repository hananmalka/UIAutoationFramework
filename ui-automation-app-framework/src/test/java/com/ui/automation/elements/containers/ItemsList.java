package com.ui.automation.elements.containers;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 06/06/2016.
 */
public abstract class ItemsList extends BaseElement {

    public ItemsList(Locator locator, Element parent) {
        super(locator, parent);
    }

    public abstract Element getItemByIndex(int index);
}
