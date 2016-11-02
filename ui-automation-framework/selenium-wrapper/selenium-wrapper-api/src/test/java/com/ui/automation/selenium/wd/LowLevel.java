package com.ui.automation.selenium.wd;

import com.ui.automation.elements.api.Element;

/**
 * Created by katzirn on 18/12/2014.
 */
public interface LowLevel {
	/**
	 * Find all elements in the DOM that meet the Element expression, wait until one of them exists.
	 * Visibility means that the element is not only displayed but also has a height or width that is greater than 0.
	 *
	 * @param element
	 * @throws Exception if not found after timeout
	 */
	void elementExistsInTheDom(Element element);

	/**
	 * Search the DOM for the element and wait until it will not exist in the DOM
	 * First it will search the exist element to understand if it loaded correct, than look for the not exist element.
	 * @param existElement the element that will be used for condition, this element must be visible
	 * @param notExistElement the element that you need to check that it is not visible
	 */
	void elementNotExistsInTheDom(Element existElement, Element notExistElement);
}
