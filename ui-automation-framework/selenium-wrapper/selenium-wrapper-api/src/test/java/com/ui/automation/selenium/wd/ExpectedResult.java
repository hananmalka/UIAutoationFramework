package com.ui.automation.selenium.wd;

import com.ui.automation.elements.api.Element;

/**
 * An interface for verifying the result of a {@link RepeatableAction}.
 */
public class ExpectedResult {

    public enum ResultType {
        VISIBLE, INVISIBLE, SELECTED, NOT_SELECTED, ENABLED, DISABLED, ELEMENT_TEXT_EQUAL, ELEMENT_TEXT_CONTAINS, NOT_CLICKABLE
}

    private ResultType resultType;
    private Element element;
    private String text = null;

    public ExpectedResult(ResultType resultType, Element element) {
        this.resultType = resultType;
        this.element = element;
    }

    /**
     * To assert expected result against strings. (using ELEMENT_TEXT_EQUAL for instance)
     * Otherwise, use {@link ExpectedResult}
     * @param resultType type of result
     * @param element the element in which to assert the result
     * @param text the expected string in the element
     */
    public ExpectedResult(ResultType resultType, Element element, String text) {
        this(resultType, element);
        this.text = text;
    }

    /**
     * Returns the type of this expected result
     * @return
     */
    public ResultType getResultType() {
        return resultType;
    }

    /**
     * Returns the element on which the expected result should apply
     * @return
     */
    public Element getElement() {
        return element;
    }

    /**
     * Returns the expected string value of the element object
     * @return
     */
    public String getText() {
        return text;
    }
}
