package com.ui.automation.selenium.wd;

import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.api.ElementCollection;

import java.util.Calendar;

/**
 * An interface for expressing DOM expectations, e.g. expecting an element to be visible in teh DOM.
 *
 * When the expectation is not met an general {@link MaasUIAutomationException} is thrown
 */
public interface Expects {

	/**
     * Strategy to use when searching for several elements
     */
    enum MultiElementExpectStrategy {
        /**
         * Expect at least one of the elements to exist in the DOM
         */
        OR,
        /**
         * Expect all of the elements to exist in the DOM
         */
        AND;
    }

    /**
     * Find all elements in the DOM that meet the Element expression, wait until one of them is visible.
     * Visibility means that the element is not only displayed but also has a height or width that is greater than 0.
     *
     * @param element
     * @throws Exception if not found after timeout
     */
    void elementToBeVisible(Element element);

    /**
     * Find all elements in the DOM that meet the Element expression, wait until one of them is has numeric values.
     * @param element
     */
    void elementToContainNumericValue(Element element);

    /**
     * Search the DOM for the element and wait until it will not be found in the DOM
     * First it will search the exist element to understand if it loaded correct, than look for the not exist element.
     * @param existElement the element that will be used for condition, this element must be visible
     * @param notExistElement the element that you need to check that it is not visible
     */
    void elementNotVisible(Element existElement, Element notExistElement);

	LowLevel lowLevel();

	boolean isAtTopOfScroll(Element element);

	void elementNotToOverflow(Element element);

    /**
     * Find all elements in the DOM that meet the Element expression, verify if location out of screen.
     * Verify location off screen by X < 0 or Y < 0
     *
     * @param element
     * @throws Exception if not found after timeout
     */
    void elementOffScreen(Element element);

    /**
     * Verifies that the text of the element underlying the element is equal to the given text.
     * @param element identifier
     * @param text reference text to compare to
     */
    void elementTextToBeEqual(Element element, final String text);

    /**
     * Verifies that the text of the element is not empty
     * @param element identifier
     */
    void elementTextNotToBeEmpty(Element element);

    /**
     * Verifies that the text value of the element underlying the element is not less than the given value.
     * @param element identifier
     * @param count reference count to compare to
     */
    void elementCountToBeAtLeast(Element element, final int count);


    static interface DateVerifier {
        /**
         * Return true if the actual and reference date fulfil a user defined condition, false otherwise
         * @param actualDate The date that was read from an HTML element
         * @param referenceDate The cate to verify against
         * @return
         */
        boolean verifyDate(Calendar actualDate, Calendar referenceDate);
    }

    static interface DateParser {
        /**
         * Parses a given String into a Calendar instance
         * @param dateStr
         * @return
         */
        Calendar parse(String dateStr);
    }

    void elementDateToCompareWith(Element element, Calendar referenceDate, DateParser dateParser, DateVerifier dateVerifier);

    /**
     * Determine whether this element is selected. This operation only applies to input
     * elements such as checkboxes, options in a select and radio buttons.
     * @param element identifier
     */
    void elementToBeSelected(Element element);

    void elementToBeFocused(Element element);

    /**
     * Determine this element is not selected. This operation only applies to input
     * elements such as checkboxes, options in a select and radio buttons.
     * @param element identifier
     */
    void elementNotToBeSelected(Element element);

    /**
     * Search for attribute in given element
     * @param element - element to search in
     * @param attrName - attribute name
     * @param attrValue - attribute value
     */
    void elementToContainAttrValue(Element element, final String attrName, final String attrValue);

    /**
     * Search for attribute in given element
     * @param element - element to search in
     * @param propertyName - CSS property name, e.g. "background-color"
     * @param propertyValue - value to verify
     */
    void elementToContainCssValue(Element element, final String propertyName, final String propertyValue);

    /**
     * Verify that attribute does not contain attribute value
     * @param element - element to search in
     * @param attrName - attribute name
     * @param attrValue - attribute value
     */
    void elementNotToContainAttrValue(Element element, final String attrName, final String attrValue);

    /**
     * Check it the element not contains attribute.
     * For example: <div attributeXX="XXX"></> it will return throw exception if attributeName = attributeXX
     * @param element
     * @param attributeName
     */
    void elementToNotContainAttribute(Element element,final String attributeName);
    /**
     * Search for text in given element
     * @param element - element to search in
     * @param text - text to search
     */
    void elementToContainText(Element element, final String text);

    /**
     * Search for text not in given element
     * @param element - element to search in
     * @param text - text to search
     */
    void elementNotToContainText(Element element, final String text);

    /**
     * Expect element to be enabled
      * @param element
     */
    void elementToBeEnabled(Element element);

    /**
     * Expect element to be disabled (this will work for input types (such: input and button)and not for any type of HTML Elements)
     *
     * @param element
     */
    void elementToBeDisabled(Element element);

    /**
     * Returns a collection of elements identified by the given element.
     * This method will wait until the backed elements collection's size is equal to the given expected size.
     *
     * @param identifier Element identifier
     * @param expectedSize Number of expected elements to return by the identifier
     * @param <T> Type of the elements to return
     * @return
     */
    <T extends Element> ElementCollection<T> collection(T identifier, int expectedSize);

    /**
     * Overloading method. This method extracts int from a element text and calls original method with it.
     * @param identifier Element identifier
     * @param elementWithSize  Element with Number of expected elements to return by the identifier. Assumes the given element contains only numeric value
     * @param <T> Type of the elements to return
     * @return
     */
    <T extends Element> ElementCollection<T> collection(T identifier, Element elementWithSize);

    /**
     * Returns a collection of elements identified by the given element.
     * This method will wait until the backed elements collection's size is at least equal to the given expected size.
     *
     * @param identifier Element identifier
     * @param expectedSize Minimum collection size of elements to return by the identifier
     * @param <T> Type of the elements to return
     * @return
     */




    <T extends Element> ElementCollection<T> elementCollectionSizeToBeAtLeast(T identifier, int expectedSize);

    /**
     * Checks if the collection of elements is displayed according to the given strategy.
     * <p/>
     * If strategy equals {@link MultiElementExpectStrategy.AND} then the function will throw an exception if at least one of the elements is not displayed.
     * <p/>
     * If strategy equals {@link MultiElementExpectStrategy.OR} then the function will throw an exception if all of the elements are not displayed
     *
     * @param strategy indicates whether all elements should be found or only some
     * @param elements elements to search for
	 * @return The visible element in case of strategy equals {@link MultiElementExpectStrategy.OR} and null in case of strategy equals {@link MultiElementExpectStrategy.AND}
     */
	Element elementsToBeVisible(MultiElementExpectStrategy strategy, Element... elements);

    /**
     * Count the number of elements appears in the DOM and return if it is as expected
     * @param element
     * @param expectedElementsCount
     * @throws MaasUIAutomationException if the number is not equals
     */
    public void numberOfElementsToBe(Element element,final int expectedElementsCount);

    /**
     * Count the number of visible elements appears in the DOM and return if it is as expected
     * @param element
     * @param expectedElementsCount
     * @throws MaasUIAutomationException if the number is not equals
     */
    public void numberOfVisibleElementsToBe(Element element, final int expectedElementsCount);

    /**
     * Check if two elements have the same text values
     * @param element1
     * @param element2
     */
    public void elementTextEquals(Element element1, Element element2);

    /**
     * Repeat performing action until expected result is achieved.
     * Useful for hitting "refresh" until expected changes are displayed
     * @param repeatableAction action to repeat
     * @param expectedResult expected result
     */
    public void repeatUntil(RepeatableAction repeatableAction, ExpectedResult expectedResult);
}
