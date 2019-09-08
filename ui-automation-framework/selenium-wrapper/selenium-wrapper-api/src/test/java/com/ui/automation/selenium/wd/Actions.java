package com.ui.automation.selenium.wd;


import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 13/01/14
 * Time: 14:52
 */
public interface Actions {


    /**
     * Drags an element a certain offset (mouse action)
     * @param element element to drag
     * @param xOffset the xOffset
     * @param yOffset the yOffser
     */
    void dragToOffset(Element element, final int xOffset, final int yOffset);

    /**
     * Drags an element a certain offset (mouse action)
     * @param element element to drag
     * @param target the target element
     */
    void dragToElement(Element element, Element target);

    /**
     * Perform mouse hover over given element
     * @param element
     */
    void hoverOver(Element element);

    /**
     * Perform click until success or timeout
     * @param element
     * @throws Exception when timeout/element not found
     */
    void click(Element element);

    /**
     * Perform double click until success or timeout
     * @param element
     * @throws Exception when timeout/element not found
     */
    public void doubleClick(Element element) ;

    /**
     * The method will select one option in select dropdown list and validate it is selected
     * @param selectElement the element must be 'select' html tag
     * @param values are the value attribute
     */
    void select(Element selectElement,final List<String> values);

    /**
     * Clear text in input box (input tag), set new value and wait until the value is set<br/>
     * The method is stable method to use in input box
     * This will check that the value set by javascript 'element.value'
     * @param element
     * @param text
     */
    void sendText(Element element, String text, CharSequence... specialKeys);


    /**
     * Same as {@link #sendText(Element, String, CharSequence...)} with custom validation.
     * A sample use case for this method's usage is sending a long string to a form field that accepts only
     * 255 characters and validating that the input contains only 255 chars from the given text
     *
     * @param element
     * @param validation
     * @param text
     */
    void sendTextCustomValidation(Element element, String text, String validation, CharSequence... specialKeys);

    /**
     * This method is used for clicking backspace in input box a given number of times.
     * @param element
     * @param times
     */
    void clickBackspaceNTimes(Element element, final int times);


    /**
     * This method is used for performing and hover operation over a given element, and then performing a click on the second given element
     * @param elementToHover the element to be hovered
     * @param elementToClick the element to be clicked
     */
    void hoverAndClick(Element elementToHover, Element elementToClick);

    /**
     * This method used to clear text value from input box.
     * It will try to clear the value until it succeed or until timeout.
     * @param element
     * @throws Exception when the value not cleared after timeout
     */
    void clearText(Element element);

    /**
     * Sends a file name to an Element that identifies an input object.
     * Does not perform text verifications after sending the file name.
     * @param input
     * @param text
     * @throws MaasUIAutomationException if element does not identify an input HTML element
     */
    void sendFileName(Element input, final String text);

    /**
     * write to the browser's console
     * @param text - the text to be written in the console
     */
    void browserConsoleLog(final String text);

    /**
     * Set the "checked" state of the given checkbox to the given value.
     * Sets tha value forcefully, without checking the previous state, i.e.
     * if the checkbox is already checked and this method is called with "true"
     * then nothing changes, otherwise the checkbox is set.
     *
     * @param checkboxWrapper The checkbox wrapper element. This element is expected to be clickable and control the checkbox state
     * @param checkboxInput The checkbox input element, on which "isSelected" vlaidation is performed
     * @param value if true checkbox will be checked, otherwise it will be unchecked
     */
    void setCheckbox(Element checkboxWrapper, Element checkboxInput, boolean value);

    /**
     * send specialKeys to element, e.g. sending Tab key without modifying existing element text
     * @param element
     * @param specialKeys
     */
    void sendSpecialKeys(final Element element, final CharSequence... specialKeys);
    void holdKeyAndClick(final Element element);

    void scrollToTop(Element element);

    /**
     * drag element to offset RELATIVE TO SOURCE ELEMENT
     * @param source the element to drag
     * @param xRelativeToElement the x axis relative to upper left corner of source (could be minus).
     * @param yRelativeToElement the y axis relative to upper left corner of source (could be minus).
     */
    void dragElementToOffset(Element source, int xRelativeToElement, int yRelativeToElement);

    void dragElementTo(Element source, Element target);

    Object executeJavaScript(String script, Object... args);

    void acceptAlert();
}
