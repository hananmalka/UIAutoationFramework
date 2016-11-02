package com.ui.automation.elements.containers;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 07/06/2016.
 */
public abstract class Editor extends BaseElement {

    public Editor(Locator locator, Element parent) {
        super(locator, parent);
    }
}
