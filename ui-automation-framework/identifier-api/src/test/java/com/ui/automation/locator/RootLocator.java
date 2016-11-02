package com.ui.automation.locator;

/**
 * A dummy locator to represent the application root, i.e. the DOM body element
 */
public class RootLocator extends Locator {

    private static Locator instance = new RootLocator();

    private RootLocator() {

    }

    public static Locator getInstance() {
        return instance;
    }

    @Override
    public LocatorType getType() {
        return LocatorType.XPATH;
    }

    @Override
    public String getExpression() {
        return "//body";
    }
}
