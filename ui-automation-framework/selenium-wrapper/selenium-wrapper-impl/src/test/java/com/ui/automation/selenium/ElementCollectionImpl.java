package com.ui.automation.selenium;

import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.api.ElementCollection;

import java.lang.reflect.Constructor;

/**
 * Collection of all elements identified by a single Element identifier
 */
public class ElementCollectionImpl<T extends Element> implements ElementCollection<T> {

    private T identifier;

    public ElementCollectionImpl(T identifier) {
        this.identifier = identifier;
    }

    @Override
    public T get(int index) {
        // Try to create a new instance with a constructor which receives a locator and a element.
        // This class assumes that the created instance MUST receive a locator in order to be able to specify a custom, more specific locator
        // which returns ONLY 1 result
        try {
            final Constructor<? extends Element> constructor = identifier.getClass().getConstructor(Locator.class, Element.class);
            // Adds a locator to specify the n-th child in the form of (//input[@id="search_query"])[2]
            return (T) constructor.newInstance(new ElementCollectionLocator(index), identifier);
        } catch (Exception e) {
            throw new MaasUIAutomationException("Could not new instance of type " + identifier.getClass().getName(), e);
        }
    }
}

