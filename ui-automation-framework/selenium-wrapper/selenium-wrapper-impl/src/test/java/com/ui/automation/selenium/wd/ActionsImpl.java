package com.ui.automation.selenium.wd;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;
import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.selenium.wd.javascript.JavaScriptUtils;
import com.thoughtworks.selenium.webdriven.JavascriptLibrary;
import org.openqa.selenium.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of {@link Actions}
 */
public class ActionsImpl extends BaseDriverExecutorImpl implements Actions {

    private JavascriptLibrary jsLib = new JavascriptLibrary();

    public ActionsImpl(DriverServices driverServices) {
        super(driverServices);
    }

    // this works in some cases but fails on others.
    public void dragToOffset(final Element element, final int xOffset, final int yOffset) {

        executeAction(element, new EventType(EventType.Message.ACTION_DRAG_DROP), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {

                WebElement webElement = driverServices.getDriver().findElement(by);
                org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
                org.openqa.selenium.interactions.Actions hoverOverElement = builder.dragAndDropBy(webElement, xOffset, yOffset);
                hoverOverElement.perform();
                return true;
            }
        });
    }

    // this works in some cases but fails on others.
    public void dragToElement(Element source, Element target){
        executeAction(source, new EventType(EventType.Message.ACTION_DRAG_DROP), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {

                WebElement srcElement = driverServices.getDriver().findElement(driverServices.getBy(source));
                WebElement targetElement = driverServices.getDriver().findElement(driverServices.getBy(target));

                org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
                org.openqa.selenium.interactions.Actions hoverOverElement = builder.dragAndDrop(srcElement, targetElement);
                hoverOverElement.perform();
                return true;
            }
        });
    }

    // This method was implemented as a result of the following issue
    // Github Open Issue - https://github.com/seleniumhq/selenium-google-code-issue-archive/issues/3604
    public void dragElementToOffset(Element source, int xRelativeToElement, int yRelativeToElement) {
        executeAction(source, new EventType(EventType.Message.ACTION_DRAG_DROP), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement srcElement = driverServices.getDriver().findElement(driverServices.getBy(source));

                JavaScriptUtils jsUtils = new JavaScriptUtils();
                String jsDND = jsUtils.getJavaScriptDNDToOffsetScript();

                ((JavascriptExecutor)driverServices.getDriver()).executeScript(jsDND, srcElement, xRelativeToElement, yRelativeToElement);
                return true;
            }
        });
    }

    public void dragElementTo(Element source, Element target){
        executeAction(source, new EventType(EventType.Message.ACTION_DRAG_DROP), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {

                WebElement srcElement = driverServices.getDriver().findElement(driverServices.getBy(source));
                WebElement targetElement = driverServices.getDriver().findElement(driverServices.getBy(target));

                JavaScriptUtils jsUtils = new JavaScriptUtils();
                String jsDND = jsUtils.getJavaScriptDNDToTargetElementScript();

                ((JavascriptExecutor)driverServices.getDriver()).executeScript(jsDND, srcElement, targetElement);

                return true;
            }
        });
    }

    public void doubleClick(Element element) {
        executeAction(element, new EventType(EventType.Message.ACTION_DOUBLE_CLICK), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                return atomicDoubleClick(webElement);
            }
        });
    }

    protected boolean atomicDoubleClick(WebElement webElement) {
        if (!webElement.isEnabled() || !webElement.isDisplayed()) {
            throw new MaasUIAutomationException("Web element is not displayed [" + webElement.isDisplayed() +
                    "] or enabled [" + webElement.isEnabled() + "]");
        }
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
        builder.doubleClick(webElement).perform();
        return true;
    }

    /**
     * Perform mouse hover over given element
     *
     * @param element
     */
    @Override
    public void hoverOver(Element element) {

        executeAction(element, new EventType(EventType.Message.ACTION_HOVER), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                return atomicHoverOver(webElement);
            }
        });
    }

    protected boolean atomicHoverOver(WebElement webElement) {
        org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
        org.openqa.selenium.interactions.Actions hoverOverElement = builder.moveToElement(webElement);
        hoverOverElement.perform();
        return true;
    }

    @Override
    public void click(Element element) {
        executeAction(element, new EventType(EventType.Message.ACTION_CLICK), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                return atomicClick(webElement);
            }
        });
    }

    @Override
    public void hoverAndClick(Element elementToHover, final Element elementToClick) {
        EventType et = new EventType(EventType.Message.ACTION_HOVER_AND_CLICK);
        et.setParams(driverServices.getXpath(elementToHover), driverServices.getXpath(elementToClick));

        executeAction(elementToHover, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElementToHover = driverServices.getDriver().findElement(by);
                org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
                org.openqa.selenium.interactions.Actions hoverOverElement = builder.moveToElement(webElementToHover);
                hoverOverElement.perform();

                By byToClick = driverServices.getBy(elementToClick);
                WebElement webElementToClick = driverServices.getDriver().findElement(byToClick);
                webElementToClick.click();
                return true;
            }
        });
    }

    protected boolean atomicClick(WebElement webElement) {
        driverServices.scrollToView(webElement);
        if (!webElement.isEnabled()) {
            // Try clicking the element only when it is ready to be clicked
            // e.g. don't click on a disabled button since Selenium is OK with it, but this is probably
            // Not the intention of the test author.
            throw new MaasUIAutomationException("Unable to click disabled element");
        }
        if (!webElement.isDisplayed()) {
            // checking that the not displayed element is part of SVG Dom.
            // If it is, click on it with JS.
            // If not, throw Exception
            logger.warning("Element '" + webElement.getTagName() + "' not displayed (by Selenium)! Going to check if it is inside SVG...");
            List<WebElement> elements = webElement.findElements(By.xpath("ancestor::*[name()='svg']"));
            if (elements.size() > 0) {
                logger.info("Element is in SVG. Clicking element by JavaScript!");
                String script = "arguments[0].click()";
                driverServices.executeJavaScript(script, webElement);
            } else {
                throw new MaasUIAutomationException("Unable to click not visible element");
            }

        } else {
            org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
            builder.moveToElement(webElement).click(webElement).perform();
            // trigger business rules in forms...
            try {
                focus(webElement);
                blur(webElement);
            } catch (StaleElementReferenceException e) {
                // ... ignore blur and focus exception when elements are not in the current element anymore
                // (because the click make you navigate to another element for instance...)
            }
        }
        return true;
    }


    @Override
    public void clearText(Element element) {
        executeAction(element, new EventType(EventType.Message.ACTION_CLEAR_TEXT), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);
                return clearText(webElement);
            }
        });
    }

    @Override
    public void sendText(final Element element, String text, final CharSequence... specialKeys) {
        sendTextCustomValidation(element, text, text, specialKeys);
    }

    @Override
    public void sendSpecialKeys(final Element element, final CharSequence... specialKeys) {
        executeAction(element, new EventType(EventType.Message.ACTION_SEND_KEYS), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);

                focus(webElement);
                webElement.sendKeys(specialKeys);
                return true;
            }
        });
    }
    // for now the key = CONTROL
    @Override
    public void holdKeyAndClick(final Element element) {
        executeAction(element, new EventType(EventType.Message.ACTION_SEND_KEYS), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);

                focus(webElement);
                org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
                builder.keyDown(Keys.CONTROL).click(webElement).keyUp(Keys.CONTROL).build().perform();
                return true;
            }
        });
    }

    @Override
    public void sendTextCustomValidation(Element element, final String text, final String validation, final CharSequence... specialKeys) {
        // executeAction(element, "Send text to element. Text = [" + text + "] validation = [" + validation + "]", new ActionExecutor() {
        EventType et = new EventType(EventType.Message.ACTION_SEND_TEXT);
        et.setParams(text, validation);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                boolean result = false;
                WebElement webElement = driverServices.getDriver().findElement(by);
                return atomicSendText(webElement, by, text, validation, specialKeys);
            }
        });
    }

    private boolean isFireFox(){
        TestProperties testProperties = ApplicationContextHolder.getApplicationContext().getBean(TestProperties.class);
        return testProperties.getBrowserType().equals("firefox");
    }
    public boolean atomicSendText(WebElement webElement, By by, String text, String validation, CharSequence... specialKeys) {
        boolean result = false;

        driverServices.scrollToView(webElement);

        if (isFireFox()) {
            focusBrowserContent(by);
        }

        focus(webElement);
        try {
            clearText(webElement);
        } catch (Exception ex) {
        }

        webElement.sendKeys(text);

        // Input validation
        String textStr = charSequenceArrayToString(text);
        String scpValue = getJSValue(webElement);
        String inTxt = getText(webElement);
        if (scpValue == null) {
            if (inTxt.trim().equals(validation)) {
                result = true;
            } else {
                logger.warning("The text value '" + inTxt + "'is not set and not match to sent text '" + textStr + "'");
            }
        } else {
            if (!(scpValue.equals(validation) || inTxt.trim().equals(validation))) {
                logger.warning("The text value is not set and not match to sent text '" + textStr + "'");
            } else {
                result = true;
            }
        }

        // Send special keys after validation because they usually change the input text, e.g.
        // sending the Enter key submits and clears the input, sending the Esc key clears the input.
        webElement.sendKeys(specialKeys);

        // Wait for the application to respond to the text changes (e.g. required field validation)
        if (result) {
            try {
                // Change focus
                // https://code.google.com/p/selenium/wiki/FrequentlyAskedQuestions#Q:_The_"onchange"_event_doesn't_fire_after_a_call
                blur(webElement);

            } catch (Exception e) {
                // ignore exception - if ENTER key was sent then the input might no longer be valid and the WebElement is stale
                logger.info("Ignoring exception on blur, " + e.getMessage());
            }
            sleep(200);
        }

        return result;
    }

    /**
     * SendText failed to send text to rich text editor in FireFox
     * This way we get focus on browser
     *
     * @param by
     */
    private void focusBrowserContent(By by) {
        WebDriver webDriver = driverServices.getDriver().switchTo().defaultContent();
        webDriver.findElement(by).sendKeys(" ");
    }

    private String charSequenceArrayToString(CharSequence... text) {
        StringBuilder b = new StringBuilder();
        for (CharSequence cs : text) {
            b.append(cs);
        }
        return b.toString();
    }

    @Override
    public void clickBackspaceNTimes(Element element, final int times) {
        executeAction(element, new EventType(EventType.Message.ACTION_CLEAR_TEXT), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);
                try {
                    for (int i = 0; i < times; i++) {
                        webElement.sendKeys(Keys.BACK_SPACE);
                    }
                } catch (Exception e) {
                    return false;
                }
                return true;
            }
        });
    }

    @Override
    public void sendFileName(Element input, final String text) {
        executeAction(input, new EventType(EventType.Message.ACTION_SEND_FILE), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);

                if (!webElement.getTagName().equalsIgnoreCase("input")) {
                    throw new MaasUIAutomationException("");
                }
                // Make sure the input element is visible (mainly for file upload dialog)
                setAttribute(webElement, "style", "visibility:visible");

                //Set attributes in order to work also for FireFox
                if (isFireFox()) {
                    setAttribute(webElement, "style", "display:block; visibility:visible");
                }

                driverServices.scrollToView(webElement);
                try {
                    clearText(webElement);
                } catch (Exception ex) {
                }
                webElement.sendKeys(text);
                return true;
            }
        });
    }

    @Override
    public void setCheckbox(final Element checkboxWrapper, final Element checkboxInput, final boolean value) {
        final EventType actionSetCheckbox = new EventType(EventType.Message.ACTION_SET_CHECKBOX);
        actionSetCheckbox.setParams(Boolean.toString(value));
        executeAction(checkboxWrapper, actionSetCheckbox, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement checkboxClickableElement = driverServices.getDriver().findElement(by);
                final By byInput = driverServices.getBy(checkboxInput);
                WebElement checkboxInputElement = driverServices.getDriver().findElement(byInput);

                if (!checkboxInputElement.getTagName().equalsIgnoreCase("input")) {
                    throw new MaasUIAutomationException("Expected an input element, got [" +
                            checkboxInputElement.getTagName() + "] instead");
                }
                if (checkboxInputElement.isSelected() && !value) {
                    return atomicClick(checkboxClickableElement);
                } else if (!checkboxInputElement.isSelected() && value) {
                    return atomicClick(checkboxClickableElement);
                }
                // No need to change the "checked" state of the input
                return true;
            }
        });
    }

    @Override
    public void browserConsoleLog(final String text) {
        try {
            String script = "console.error('" + text.replace('\'', '"') + "')";
            driverServices.executeJavaScript(script);
        } catch (Exception ex) {
            logger.info("Failed to write to console log: " + ex);
        }
    }

    @Override
    public void select(Element selectElement, final List<String> values) {
        executeAction(selectElement, new EventType(EventType.Message.ACTION_SELECT_OPTION), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);
                Select select = new Select(webElement);
                //check that the combobox support multiple values on case that more than one value
                if (values.size() > 1) {
                    if (!select.isMultiple()) {
                        failedEx.setInner(new MaasUIAutomationException("The selected box does not support multiple selection and you want to select those values: " + values));
                        return false;
                    }
                }
                //select.deselectAll();
                //select all value in list
                for (String value : values) {
                    try {
                        select.selectByValue(value);
                    } catch (Exception ex) {
                        failedEx.setInner(new MaasUIAutomationException("Failed to select option from drop down by value [" + value + "]", ex));
                        return false;
                    }
                }

                // validate all options are selected by their value.

                //put selected values in list of string
                List<String> selectedValues = new ArrayList<>();
                for (WebElement selectedElem : select.getAllSelectedOptions()) {
                    selectedValues.add(selectedElem.getAttribute("value"));
                }
                //validate all values are exist in selected list
                for (String value : values) {
                    if (!selectedValues.contains(value)) {
                        failedEx.setInner(new MaasUIAutomationException("Automation Error: The selected values is not as expected!! Expected: '" + value + "' but was was not found in selected list: '" + selectedValues + "'"));
                        return false;
                    }
                }
                return true;
            }
        });
    }

    private boolean clearText(WebElement webElement) {
        driverServices.scrollToView(webElement);
        webElement.clear();
        webElement.sendKeys(Keys.END);
        String scpValue = getJSValue(webElement);
        if (scpValue == null) {
            return true;
        }
        while (scpValue.length() > 0) {
            webElement.sendKeys("\b");
            scpValue = getJSValue(webElement);
        }
        scpValue = ((String) driverServices.executeJavaScript("return arguments[0].value", webElement));
        return (scpValue.trim().length() == 0);
    }

    private String getJSValue(WebElement webElement) {
        return ((String) driverServices.executeJavaScript("return arguments[0].value", webElement));
    }

    private void focus(WebElement webElement) {
        jsLib.callEmbeddedSelenium(driverServices.getDriver(), "triggerEvent", webElement, "focus");
    }

    private void blur(WebElement webElement) {
        jsLib.callEmbeddedSelenium(driverServices.getDriver(), "triggerEvent", webElement, "blur");
    }

    private void setAttribute(WebElement element, String attributeName, String value) {
        driverServices.executeJavaScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, attributeName, value);
    }

    // get attribute - Ionut's request.

    @Override
    public void scrollToTop(Element element) {
        executeAction(element, new EventType(EventType.Message.SCROLL_TO_TOP), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.executeJavaScript("arguments[0].scrollTop = 0", webElement);
                return true;
            }
        });
    }

    @Override
    public Object executeJavaScript(String script, Object... args){
        return driverServices.executeJavaScript(script, args);
    }

    // find elements
    @Override
    public void acceptAlert(){
        driverServices.getDriver().switchTo().alert().accept();
    }

}

