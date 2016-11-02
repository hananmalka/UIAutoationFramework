package com.ui.automation.selenium;

import com.ui.automation.locator.Locator;
import com.ui.automation.locator.LocatorType;
import com.ui.automation.elements.api.ElementCollection;

/**
 * A special internal type of locator for specifying the n-th child of an element that returns multiple results.
 * For example this locator can be used for returning the 3rd element in a list.
 *
 * @see {@link ElementCollection}
 */
public class ElementCollectionLocator extends Locator {

    private int index;

    public ElementCollectionLocator(int index) {
        this.index = index;
    }

    @Override
    public LocatorType getType() {
        return LocatorType.XPATH;
    }

    @Override
    public String getExpression() {
        return this.getClass().getName();
    }

    public int getIndex() {
        return index;
    }
}
