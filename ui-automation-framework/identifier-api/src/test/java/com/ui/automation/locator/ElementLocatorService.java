package com.ui.automation.locator;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.impl.RootElement;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * A service for translating a element into the ordered list of locators from its ancestors down
 */
@Component
public class ElementLocatorService {

    /**
     * Return the ordered locators list starting from the top most ancestor down to
     * the given element
     * @param element
     * @return
     */
    public List<Locator> getLocators(Element element) {
        ArrayList<Locator> locators = new ArrayList<Locator>(10);

        Element current = element;

        while (current != null && !current.equals(RootElement.getInstance())) {
            locators.add(current.getLocator());
            current = current.getParent();
        }
        // Add the root element locator if the list is empty (saves an unnecessary Selenium iteration when finding elements)
        if (locators.isEmpty()) {
            locators.add(RootElement.getInstance().getLocator());
        }

        return locators;
    }
}
