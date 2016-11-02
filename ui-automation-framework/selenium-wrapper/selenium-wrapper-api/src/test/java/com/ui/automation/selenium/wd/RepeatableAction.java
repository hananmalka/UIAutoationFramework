package com.ui.automation.selenium.wd;

import com.ui.automation.elements.api.Element;

/**
 * An interface for repeating an action until an expected result is achieved.
 * <p/>
 * The action is expected to be able to perform repeatedly, i.e. should not change the current element.
 * <p/>
 * An example use case is clicking on "refresh" button until the expected result appears.
 * <p/>
 * @see ExpectedResult
 */
public class RepeatableAction {

    public enum ActionType {
        CLICK, DOUBLE_CLICK, HOVER
    }

    private ActionType actionType;
    private Element element;

    public RepeatableAction(ActionType actionType, Element element) {
        this.actionType = actionType;
        this.element = element;
    }

    /**
     * Returns the type of the action to perform
     * @return
     */
    public ActionType getActionType() {
        return actionType;
    }

    /**
     * Returns the element which is the target of the action
     * @return
     */
    public Element getElement() {
        return element;
    }
}
