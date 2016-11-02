package com.ui.automation.locator;

/**
 * Identifier for DOM elements that allows chaining
 */
public abstract class Locator {

    /**
     * Create a new Locator that locates an element based on its id, e.g. <div id="foo"/>
     * @param id
     * @return
     */
    public static Locator id(String id) {
        return new LocatorData(LocatorType.ID, id);
    }

    /**
     * Create a new Locator that locates an element based on its data-aid attribute, e.g. <div data-aid="foo"/>
     * @param dataAid
     * @return
     */
    public static Locator dataAid(String dataAid) {
        return new LocatorData(LocatorType.DATA_AID, dataAid);
    }

    /**
     * Create a new Locator that locates an element based on one of its class names, e.g. <div class="foo"/>
     * @param className
     * @return
     */
    public static Locator className(String className) {
        return new LocatorData(LocatorType.CLASS_NAME, className);
    }

    /**
     * Create a new Locator that locates an element based on one a CSS selector, e.g. div[foo] will locate
     * div elements that have a foo attribute, e.g. <div foo/>
     * @param selector
     * @return
     */
    public static Locator css(String selector) {
        return new LocatorData(LocatorType.CSS, selector);
    }

    /**
     * Create a new Locator that locates an element based on an arbitrary XPATH
     * @param xpath
     * @return
     */
    public static Locator xpath(String xpath) {
        return new LocatorData(LocatorType.XPATH, xpath);
    }

    /**
     * Returns the locator type
     * @return
     */
    public abstract LocatorType getType();

    /**
     * Returns the locator expression
     * @return
     */
    public abstract String getExpression();

    protected static class LocatorData extends Locator {
        LocatorType type;
        String expression;

        LocatorData(LocatorType type, String expression) {
            this.type = type;
            this.expression = expression;
        }

        @Override
        public LocatorType getType() {
            return type;
        }

        @Override
        public String getExpression() {
            return expression;
        }

        @Override
        public String toString() {
            return "LocatorData " + type + ": " + expression;
        }
    }
}
