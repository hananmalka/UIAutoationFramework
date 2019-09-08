package com.ui.automation.elements.base;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.impl.RootElement;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 09/01/14
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
public class BaseTopLevelElement extends BaseElement {

    public BaseTopLevelElement(Locator locator) {
        super(locator, RootElement.getInstance());
    }
}
