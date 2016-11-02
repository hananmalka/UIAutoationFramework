package com.ui.automation.elements.api;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.impl.RootElement;

/**
 * An abstraction of part of the screen.
 * <p/>
 * This interface correlates to an MVC view in the application
 * <p/>
 * Elemnets are hierarchical to better identify specific DOM elements
 */
public interface Element {

    /**
     * Returns the element locator
     * @return
     */
    Locator getLocator();

    /**
     * Returns the element parent.
     * Top level elements should return {@link RootElement}
     * @return
     */
    Element getParent();

    /**
     * Expects if element is visible
     * @return
     */
    Element expectVisible();

    /**
     * Expects element not visible in its direct parent element
     * @return
     */
    Element expectNotVisible();
}
