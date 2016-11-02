package com.ui.automation.selenium.wd;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.api.ElementCollection;
import com.ui.automation.selenium.ElementCollectionImpl;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.text.SimpleDateFormat;
import java.util.*;

import static org.apache.commons.lang.StringEscapeUtils.escapeHtml;

/**
 * Implementation of {@link Expects}
 */
public class ExpectsImpl extends BaseDriverExecutorImpl implements Expects {
    private LowLevelImpl lowLevel;

    public ExpectsImpl(DriverServices driverServices) {
        super(driverServices);
        lowLevel = new LowLevelImpl(driverServices);
    }

    @Override
    public void elementToBeVisible(Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_VISIBLE), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return atomicElementToBeVisible(by);
            }
        });
    }

    private boolean atomicElementToBeVisible(By by) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        if (!webElement.isDisplayed()) {

            logger.warning("Element '" + webElement.getTagName() + "' not displayed (by Selenium)! Going to check if it is inside SVG...");
            List<WebElement> elements = webElement.findElements(By.xpath("ancestor::*[name()='svg']"));
            if (elements.size() > 0) {
                logger.info("Element is in SVG. checking element visibility by JavaScript!");
                String script = "return arguments[0].hidden";
                if ((Boolean) driverServices.executeJavaScript(script, webElement)) {
                    throw new MaasUIAutomationException("Given element found in SVG but it is not visible(hidden) !!!");
                }
            } else {
                throw new MaasUIAutomationException("Given element found but it is not visible!!!");
            }
        }
        try{
            if (webElement.getSize().getHeight() == 0) {
                throw new MaasUIAutomationException("Given element height=0, so it is not visible!!!");
            }
            if (webElement.getSize().getWidth() == 0) {
                throw new MaasUIAutomationException("Given element width=0, so it is not visible!!!");
            }
        }
        catch (org.openqa.selenium.remote.UnreachableBrowserException unreachBrowEx){
            return webElement.isDisplayed();
        }

        return true;
    }

    @Override
    public void elementToContainNumericValue(Element element) {
        EventType et = new EventType(EventType.Message.EXPECT_NUMERIC_VALUE);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                List<WebElement> webElements = driverServices.getDriver().findElements(by);
                for (WebElement webElement : webElements) {
                    if (!StringUtils.isNumeric(webElement.getText()) || webElement.getText().isEmpty()) {
                        throw new MaasUIAutomationException("Given element does not contain numeric values!!!");
                    }
                }
                return true;
            }
        });
    }

    @Override
    public boolean isAtTopOfScroll(Element element) {
        final By by = driverServices.getBy(element);
        return driverServices.isAtTopOfScroll(by);
    }

    @Override
    public void elementNotVisible(final Element existElement, Element notVisibleElement) {

        String xpathVisElement = driverServices.getXpath(existElement);
        EventType et = new EventType(EventType.Message.EXPECT_INVISIBLE);
        et.setParams(xpathVisElement);
        executeAction(notVisibleElement, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return atomicElementNotVisible(by, existElement);

            }
        });
    }


    @Override
    public LowLevel lowLevel() {
        return lowLevel;
    }

    @Override
    public void elementNotToOverflow(Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_NOT_OVERFLOW), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return atomicElementNotToOverflow(by);
            }
        });
    }

    private boolean atomicElementNotToOverflow(By by) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        Boolean result = (Boolean) driverServices.executeJavaScript("return arguments[0].scrollHeight <= arguments[0].offsetHeight && arguments[0].scrollWidth <= arguments[0].offsetWidth", webElement);
        return result;
    }

    private boolean atomicElementNotVisible(By by, Element existElement) {
        final By byExist = driverServices.getBy(existElement);
        // Wait for the existing element to... exist :)
        driverServices.getDriver().findElement(byExist);
        WebElement existWebElement;
        try {
            existWebElement = driverServices.getDriver().findElement(by);
        } catch (NoSuchElementException ex) {
            return true;
        }

        if (!existWebElement.isDisplayed()) {
            return true;
        }
        throw new MaasUIAutomationException("Element is still displayed " + by);
    }

    @Override
    public void elementOffScreen(final Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_OFF_SCREEN), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {

                WebElement el = driverServices.getDriver().findElement(by);
                if (el.getLocation().getY() < 0 || el.getLocation().getX() < 0) {
                    return true;
                }
                return false;
            }

        });
    }

    private boolean atomicElementTextToBeEqual(By by, String text) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        return webElement.getText().equals(text);
    }

    private boolean atomicElementTextContains(By by, String text) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        return webElement.getText().contains(text);
    }

    private boolean atomerElementNotToBeSelected(By by) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        driverServices.scrollToView(webElement);
        return !webElement.isSelected();
    }

    private boolean atomicElementToBeSelected(By by) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        driverServices.scrollToView(webElement);
        return webElement.isSelected();
    }

    private boolean atomicElementToBeFocused(By by) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        WebElement activeWebElement = driverServices.getDriver().switchTo().activeElement();
        driverServices.getDriver().switchTo().defaultContent();
        //switch to the current element
        if (webElement.equals(activeWebElement)) {
            return true;
        }
        String elementHTML = driverServices.getElementHTML(activeWebElement);
        if (elementHTML.length() > 155) {
            elementHTML = escapeHtml(elementHTML.substring(0, 135) + " ..... " + elementHTML.substring(elementHTML.length() - 15, elementHTML.length()));
        }
        throw new MaasUIAutomationException("The given element is not focused! " + "The focused element is: " + elementHTML);
    }

    @Override
    public void elementToBeSelected(Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_SELECTED), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return atomicElementToBeSelected(by);
            }
        });
    }


    @Override
    public void elementToBeFocused(Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_FOCUSED), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return atomicElementToBeFocused(by);
            }
        });
    }


    @Override
    public void elementNotToBeSelected(Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_NONELECTED), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return atomerElementNotToBeSelected(by);
            }
        });
    }

    @Override
    public void elementTextToBeEqual(Element element, final String text) {
        EventType et = new EventType(EventType.Message.EXPECT_TEXT_EQUALS);
        et.setParams(text);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);
                if (!getText(webElement).trim().equals(text)) {
                    throw new MaasUIAutomationException("Element text '" + getText(webElement).trim() + "' is not as expected '" + text + "'");
                }
                return true;
            }
        });
    }

    @Override
    public void elementTextNotToBeEmpty(Element element) {
        EventType et = new EventType(EventType.Message.EXPECT_TEXT_EQUALS);
        et.setParams("");
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);
                if (getText(webElement).trim().equals("")) {
                    throw new MaasUIAutomationException("Element text is EMPTY although it shouldn't be!");
                }
                return true;
            }
        });
    }



    @Override
    public void elementDateToCompareWith(Element element, final Calendar referenceDate, final DateParser dateParser, final DateVerifier dateVerifier) {
        final EventType eventType = new EventType(EventType.Message.EXPECT_DATE_IN_RANGE, new SimpleDateFormat("dd-MMM-yyyy").format(referenceDate.getTime()));
        executeAction(element, eventType, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                // Applicative date representation e.g. 09/11/2003
                String dateStr = getText(webElement);
                Calendar actualDate = dateParser.parse(dateStr);
                return dateVerifier.verifyDate(actualDate, referenceDate);
            }
        });
    }

    @Override
    public void elementCountToBeAtLeast(Element element, final int count) {
        EventType et = new EventType(EventType.Message.EXPECT_MIN_ELEMENT_COUNT);
        et.setParams(String.valueOf(count));
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                driverServices.scrollToView(webElement);
                String actualText = getText(webElement).trim();
                try {
                    int actualTextValue = Integer.parseInt(actualText);
                    if (actualTextValue < count) {
                        throw new MaasUIAutomationException("Actual value is: '" + actualText + "'. Expected value to be at least '" + count + "'");
                    }
                } catch (NumberFormatException ex) {
                    throw new MaasUIAutomationException("Can't parse actual value '" + actualText + "' to int.", ex);
                }
                return true;
            }
        });
    }

    @Override
    public void elementToContainAttrValue(Element element, final String attrName, final String attrValue) {
        EventType et = new EventType(EventType.Message.EXPECT_ATT_VALUE_CONTAIN);
        et.setParams(attrName, attrValue);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                if (webElement.getAttribute(attrName).contains(attrValue)) {
                    return true;
                } else {
                    throw new MaasUIAutomationException("Element attribute '@" + attrName + "=" +
                            webElement.getAttribute(attrName) + " while expecting @" + attrName + " to contains(" + attrValue + ")");
                }
            }
        });
    }

    @Override
    public void elementToContainCssValue(Element element, final String propertyName, final String propertyValue) {
        EventType et = new EventType(EventType.Message.EXPECT_CSS_VALUE_CONTAIN);
        et.setParams(propertyName, propertyValue);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                String cssValue = webElement.getCssValue(propertyName);
                if (cssValue.contains(propertyValue)) {
                    return true;
                } else {
                    throw new MaasUIAutomationException("Element CSS property " + propertyName + "=" +
                            cssValue + " while expecting " + propertyName + " to contains(" + propertyValue + ")");
                }
            }
        });
    }

    @Override
    public void elementNotToContainAttrValue(Element element, final String attrName, final String attrValue) {
        EventType et = new EventType(EventType.Message.EXPECT_ATT_VALUE_NOT_CONTAIN);
        et.setParams(attrName, attrValue);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                if (!webElement.getAttribute(attrName).contains(attrValue)) {
                    return true;
                } else {
                    throw new MaasUIAutomationException("Element attribute '@" + attrName + "=" +
                            webElement.getAttribute(attrName) + " while expecting @" + attrName + " to contains[" + attrValue + "]");
                }
            }
        });
    }

    @Override
    public void elementToNotContainAttribute(Element element, final String attributeName) {
        EventType et = new EventType(EventType.Message.EXPECT_ATTRIBUTE_NOT_EXIST);
        et.setParams(attributeName);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                WebElement webElement = driverServices.getDriver().findElement(by);
                if (webElement.getAttribute(attributeName) == null) {
                    return true;
                } else {
                    throw new MaasUIAutomationException("Attribute '@" + attributeName + "' is exist in the element while expecting to not exist!");
                }
            }
        });

    }

    @Override
    public void elementToContainText(Element element, final String text) {
        EventType et = new EventType(EventType.Message.EXPECT_TEXT_CONTAINS);
        et.setParams(text);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                final List<WebElement> elements = driverServices.getDriver().findElements(by);
                if (elements.size() == 0) {
                    throw new NoSuchElementException("Could not located element!!");
                }
                String elementText = "";
                for (WebElement element : elements) {
                    elementText = getText(element);
                    if (elementText.contains(text)) {
                        return true;
                    }
                }
                throw new MaasUIAutomationException("Element text '" + elementText + "' does not contain expected text: '" + text + "'");

            }
        });
    }

    @Override
    public void elementNotToContainText(Element element, final String text) {
        EventType et = new EventType(EventType.Message.EXPECT_TEXT_NOT_CONTAINS);
        et.setParams(text);
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                final List<WebElement> elements = driverServices.getDriver().findElements(by);
                if (elements.size() == 0) {
                    return true;
                }
                String elementText = "";
                for (WebElement element : elements) {
                    elementText = getText(element);
                    if (elementText.contains(text)) {
                        throw new MaasUIAutomationException("Element text '" + elementText + "' contain unexpected text: '" + text + "'");
                    }
                }
                return true;
            }
        });
    }

    @Override
    public void elementToBeEnabled(Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_ENABLED), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return atomicElementToBeEnabled(by);
            }
        });
    }

    private boolean atomicElementToBeEnabled(By by) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        if (!webElement.isEnabled()) {
            throw new MaasUIAutomationException("Element is not enabled while expecting to be enabled");
        }
        return true;
    }

    @Override
    public void elementToBeDisabled(Element element) {
        executeAction(element, new EventType(EventType.Message.EXPECT_DISABLED), new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                return ExpectsImpl.this.atomicElementToBeDisabled(by);
            }
        });
    }

    private boolean atomicElementToBeDisabled(By by) {
        WebElement webElement = driverServices.getDriver().findElement(by);
        if (webElement.isEnabled()) {
            throw new MaasUIAutomationException("Element is enabled while expecting to be disabled");
        }
        return true;
    }

    @Override
    public void numberOfElementsToBe(Element element, final int expectedElementsCount) {
        EventType et = new EventType(EventType.Message.EXPECT_EQUALS_ELEMENT_COUNT);
        et.setParams(String.valueOf(expectedElementsCount));
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                List<WebElement> webElements = driverServices.getDriver().findElements(by);
                // In case we didn't find element while expecting to have at least one, throw this exception to be able to locate the problematic by
                return verifyExpectedElementsCount(webElements, expectedElementsCount);
            }
        });
    }

    private boolean verifyExpectedElementsCount(List<WebElement> webElements, int expectedElementsCount) {
        if (expectedElementsCount != 0 && webElements.size() == 0) {
            throw new MaasUIAutomationException("Could not located element!!! Expected element number to be [" + expectedElementsCount + "] but was [0]");
        }
        if (webElements.size() != expectedElementsCount) {
            throw new MaasUIAutomationException("The number of elements is " + webElements.size() + " while expecting to be " + expectedElementsCount);
        }
        return true;
    }

    @Override
    public void numberOfVisibleElementsToBe(Element element, final int expectedElementsCount) {
        EventType et = new EventType(EventType.Message.EXPECT_EQUALS_VISIBLE_ELEMENT_COUNT);
        et.setParams(String.valueOf(expectedElementsCount));
        executeAction(element, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                List<WebElement> webElements = driverServices.getDriver().findElements(by);
                List<WebElement> toBeRemoved = new ArrayList<>();
                for (WebElement element : webElements) {
                    if (!element.isDisplayed()) {
                        toBeRemoved.add(element);
                    }
                }
                webElements.removeAll(toBeRemoved);
                // In case we didn't find element while expecting to have at least one, throw this exception to be able to locate the problematic by
                return verifyExpectedElementsCount(webElements, expectedElementsCount);
            }
        });
    }

    @Override
    public <T extends Element> ElementCollection<T> collection(final T identifier, final int expectedSize) {
        EventType et = new EventType(EventType.Message.EXPECT_ELEMENT_COLLECTION_SIZE);
        et.setParams(String.valueOf(expectedSize));
        executeAction(identifier, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                List<WebElement> elements = driverServices.getDriver().findElements(by);
                // In case we didn't find element while expecting to have at least one, throw this exception to be able to locate the problematic by
                if (expectedSize != 0 && elements.size() == 0) {
                    throw new NoSuchElementException("Could not located element!!! Expected element number to be [" + expectedSize + "] but was [0]");
                }
                if (elements.size() != expectedSize) {
                    failedEx.setInner(new MaasUIAutomationException("Expected to find " + expectedSize + " elements but found " + elements.size() + " elements by " + by));
                }
                return elements.size() == expectedSize;
            }
        });
        return new ElementCollectionImpl<T>(identifier);
    }

    @Override
    public <T extends Element> ElementCollection<T> collection(T identifier, final Element elementWithSize) {
        final By elementBy = driverServices.getBy(elementWithSize);
        WebElement webElement = driverServices.getDriver().findElement(elementBy);
        try {
            int expectedSize = Integer.parseInt(getText(webElement));
            return collection(identifier, expectedSize);

        } catch (NumberFormatException e) {
            throw new MaasUIAutomationException("Expect to find element with only Integer in the text! Text found: [" + getText(webElement) + "]");
        }
    }

    @Override
    public <T extends Element> ElementCollection<T> elementCollectionSizeToBeAtLeast(final T identifier, final int expectedCount) {
        EventType et = new EventType(EventType.Message.EXPECT_ELEMENT_COLLECTION_SIZE_AT_LEAST);
        et.setParams(String.valueOf(expectedCount));
        executeAction(identifier, et, new ActionExecutor() {
            @Override
            public boolean execute(By by, ExceptionHolder failedEx) {
                List<WebElement> elements = driverServices.getDriver().findElements(by);
                // In case we didn't find element while expecting to have at least one, throw this exception to be able to locate the problematic by
                if (expectedCount != 0 && elements.size() == 0) {
                    throw new NoSuchElementException("Could not located element!!! Expected element collection size to be at least [" + expectedCount + "] but was [0]");
                }
                if (elements.size() < expectedCount) {
                    failedEx.setInner(new MaasUIAutomationException("Expected to find at least " + expectedCount + " elements but found " + elements.size() + " elements by " + by));
                }
                return elements.size() >= expectedCount;
            }
        });
        return new ElementCollectionImpl<T>(identifier);
    }

    private Element anyElementVisible(final Element... elements) {
        final EventType eventType = new EventType(EventType.Message.EXPECT_ANY_ELEMENT_VISIBLE);

        final TestEvent testEvent = notifyBeforeElementArray(eventType, elements);

        // Throws a non-specified exception if none of the elements is visible
        try {
            if (driverServices.isAngularDefined()) {
                this.waitForAngular();
            }
            return driverServices.getFluentWait().until(new ExpectedCondition<Element>() {
                @Override
                public Element apply(org.openqa.selenium.WebDriver webDriver) {

                    for (int i = 0; i < elements.length; i++) {
                        Element element = elements[i];
                        final By by = driverServices.getBy(element);


                        List<WebElement> webElements = driverServices.getDriver().findElements(by);
                        for (WebElement webElement : webElements) {
                            if (webElement.isDisplayed()) {
                                // found a visible element
                                return element;
                            }
                        }
                    }
                    // No visible element was found
                    return null;
                }
            });
        } finally {
            notifyAfterEvent(testEvent);
        }
    }

    private TestEvent notifyBeforeElementArray(EventType eventType, Element[] elements) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < elements.length; i++) {
            Element element = elements[i];
            final By by = driverServices.getBy(element);
            sb.append(by.toString()).append("<br/>");
        }
        final Map<String, String> messageDetails = new HashMap<>();
        messageDetails.put("Locators", sb.toString());
        final TestEvent testEvent = new TestEvent(eventType, "Locating by multiple elements", messageDetails);
        notifyBeforeEvent(testEvent);
        return testEvent;
    }

    private void allElementsVisible(final Element... elements) {
        final EventType eventType = new EventType(EventType.Message.EXPECT_ALL_ELEMENTS_VISIBLE);

        final TestEvent testEvent = notifyBeforeElementArray(eventType, elements);

        try {
            if (driverServices.isAngularDefined()) {
                this.waitForAngular();
            }
            // Throws a non-specified exception if none of the elements is visible
            driverServices.getFluentWait().until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(org.openqa.selenium.WebDriver webDriver) {
                    for (int i = 0; i < elements.length; i++) {
                        Element element = elements[i];
                        final By by = driverServices.getBy(element);
                        List<WebElement> elements = driverServices.getDriver().findElements(by);
                        // If the element does not indicate any WebElement then it is considered as invisible
                        if (elements.isEmpty()) {
                            return false;
                        }
                        for (WebElement webElement : elements) {
                            if (!webElement.isDisplayed()) {
                                // found a visible element
                                return false;
                            }
                        }
                    }
                    // All elements are visible
                    return true;
                }
            });
        } finally {
            notifyAfterEvent(testEvent);
        }
    }

    @Override
    public Element elementsToBeVisible(final MultiElementExpectStrategy strategy, final Element... elements) {
        switch (strategy) {
            case OR:
                return anyElementVisible(elements);
            case AND:
                allElementsVisible(elements);
                return null;
            default:
                throw new MaasUIAutomationException("Unknown strategy " + strategy);
        }
    }

    @Override
    public void elementTextEquals(final Element element1, final Element element2) {
        executeAction(element1, new EventType(EventType.Message.EXPECT_ELEMENTS_TEXT_EQUALITY), new ActionExecutor() {
            @Override
            public boolean execute(By element1By, ExceptionHolder failedEx) {
                final By element2By = driverServices.getBy(element2);
                WebElement w1 = driverServices.getDriver().findElement(element1By);
                WebElement w2 = driverServices.getDriver().findElement(element2By);
                String p1Txt = getText(w1).trim();
                String p2Txt = getText(w2).trim();

                if (!p1Txt.equals(p2Txt)) {
                    failedEx.setInner(new MaasUIAutomationException("Element 1 text [" + p1Txt + "] is not equals to element2 text [" + p2Txt + "]"));
                    return false;
                }
                return true;
            }
        });
    }

    @Override
    public void repeatUntil(final RepeatableAction repeatableAction, final ExpectedResult expectedResult) {
        DriverTestContext driverTestContext = ApplicationContextHolder.getApplicationContext().getBean(DriverTestContext.class);
        final Actions actions = driverTestContext.getDriver().actions();
        final Element repeatableActionElement = repeatableAction.getElement();
        EventType et = new EventType(EventType.Message.REPEATABLE_ACTION);
        et.setParams(repeatableAction.getActionType().name(), expectedResult.getResultType().name());
        executeAction(repeatableActionElement, et, new ActionExecutor() {
            int relaxingPeriodMillis = 0;

            @Override
            public boolean execute(By actionBy, ExceptionHolder failedEx) {
                // Wait for the relaxing period so that consecutive executions
                // take a little bit more time between them to let the application react
                // to changes
                sleep(relaxingPeriodMillis);
                relaxingPeriodMillis += 100; // Wait for another 100 milliseconds the next time around

                final By expectedBy = driverServices.getBy(expectedResult.getElement());
                try {
                    executeRepeatableAction();
                } catch (RuntimeException rte) {
                    // Swallow exception and ignore - this action might fail but the expected result is already there
                    rte.printStackTrace();
                }
                return expectResult(expectedBy, expectedResult.getText());
            }

            private boolean expectResult(By expectedBy, String expectedText) {
                boolean result;
                switch (expectedResult.getResultType()) {
                    case DISABLED:
                        result = atomicElementToBeDisabled(expectedBy);
                        break;
                    case ENABLED:
                        result = atomicElementToBeEnabled(expectedBy);
                        break;
                    case INVISIBLE:
                        result = atomicElementNotVisible(expectedBy, null); // WTF??
                        break;
                    case VISIBLE:
                        result = atomicElementToBeVisible(expectedBy);
                        break;
                    case SELECTED:
                        result = atomicElementToBeSelected(expectedBy);
                        break;
                    case NOT_SELECTED:
                        result = atomerElementNotToBeSelected(expectedBy);
                        break;
                    case ELEMENT_TEXT_EQUAL:
                        if (expectedText == null)
                            throw new IllegalArgumentException("Expected string can't be null");
                        result = atomicElementTextToBeEqual(expectedBy, expectedText);
                        break;
                    case ELEMENT_TEXT_CONTAINS:
                        if (expectedText == null)
                            throw new IllegalArgumentException("Expected string can't be null");
                        result = atomicElementTextContains(expectedBy, expectedText);
                        break;
                    case NOT_CLICKABLE:
                        WebElement webElement = driverServices.getDriver().findElement(expectedBy);
                        try {
                            webElement.click();
                            result = false;
                        } catch (WebDriverException e) {
                            e.getMessage().contains("Element is not clickable");
                            result = true;
                        }
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown option: " + expectedResult.getResultType());
                }
                return result;

            }

            private void executeRepeatableAction() {
                // Casting to concrete implementation in order oto use protected methods that support
                // the repeatable action concept better by performing the action and expectation atomically within the loop
                // instead of looping on each one separately.
                ActionsImpl actionsImpl = ((ActionsImpl) actions);
                WebElement webElement = driverServices.getDriver().findElement(driverServices.getBy(repeatableActionElement));
                switch (repeatableAction.getActionType()) {
                    case CLICK:
                        actionsImpl.atomicClick(webElement);
                        break;
                    case DOUBLE_CLICK:
                        actionsImpl.atomicDoubleClick(webElement);
                        break;
                    case HOVER:
                        actionsImpl.atomicHoverOver(webElement);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown option: " + repeatableAction.getActionType());
                }
            }
        });
    }

    @Override
    protected void notifyAfterEvent(TestEvent ev) {
        driverServices.getVisual().checkWindow();
        super.notifyAfterEvent(ev);
    }
}
