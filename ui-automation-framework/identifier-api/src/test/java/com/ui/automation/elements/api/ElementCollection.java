package com.ui.automation.elements.api;

/**
 * Represents a collection of elements with a common locator hierarchy, e.g. rows in a grid located by the row css
 */
public interface ElementCollection<T extends Element> {

    /**
     * Returns a single element from the collection based on its index.
     * <p/>
     * Index parameter is zero-based.
     * <p/>
     *
     * IMPORTANT: The order of the returned elements reflects their viewing order as rendered on the screen, *NOT* their DOM order.
     * Elements are returned by their order from the top of the screen, higher elemesnt are returned first.
     * If elements have the same height than elements on the left are returned first.
     *
     * @param index index of element to return
     * @return
     */
    T get(int index);
}
