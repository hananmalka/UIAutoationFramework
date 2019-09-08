package com.ui.automation.elements.impl;

import com.ui.automation.locator.RootLocator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.locator.Locator;

/**
 * Dummy object that represents the locator for a top level element
 */
public class RootElement implements Element {

    private static RootElement instance = new RootElement();

    private RootElement() {

    }

    public static Element getInstance() {
        return instance;
    }

    @Override
    public Locator getLocator() {
        return RootLocator.getInstance();
    }

    @Override
    public Element getParent() {
        return instance;
    }

    @Override
    public Element expectVisible() {
        // it is special element, should never throw exception
        return this;
    }

    @Override
    public Element expectNotVisible() {
        // it is special element, should never throw exception
        return this;
    }

}
