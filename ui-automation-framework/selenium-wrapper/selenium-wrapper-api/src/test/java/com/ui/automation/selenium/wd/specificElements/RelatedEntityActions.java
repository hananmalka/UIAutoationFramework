package com.ui.automation.selenium.wd.specificElements;

import com.ui.automation.elements.api.Element;

public interface RelatedEntityActions {
    /**
     * execute javascript method to select item by set the hover property to true (using javascript)
     * $($0).scope().itemIsHovered = true
     * @param element
     */
    void selectItem(Element page);
}
