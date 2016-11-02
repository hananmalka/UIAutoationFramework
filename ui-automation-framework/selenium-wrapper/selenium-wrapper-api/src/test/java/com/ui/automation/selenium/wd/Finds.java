package com.ui.automation.selenium.wd;

import com.ui.automation.elements.api.Element;

/**
 * An interface for providing element location information
 */
public interface Finds {

    /**
     * Returns the HTML element node index within its parent.
     *
     * For example for the following structure the method would return 2 for a element that locates
     * a div with class FINDME
     * <code>
     *     <span>
     *         <div/>
     *         <div/>
     *         <div class="FINDME"/>
     *         <div/>
     *     </span>
     * </code>
     * @param element element to find
     * @return
     */
    int index(Element element);

    /**
     * Get the text content of the element
     * @param element element to get text
     * @return text
     */
    String getText(Element element);

    /**
     * Get the text content of the element
     * @param element element to get text
     * @return text
     */
    String getTagName(Element element);

}
