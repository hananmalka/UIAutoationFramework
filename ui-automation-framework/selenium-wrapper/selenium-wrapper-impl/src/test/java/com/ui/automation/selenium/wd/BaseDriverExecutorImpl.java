package com.ui.automation.selenium.wd;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.app.eventBus.TestEventListener;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.api.ElementTranslator;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.common.element.config.TestProperties;
import org.apache.commons.lang3.text.WordUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Base class for Selenium DI on top of WebDriver
 */
public abstract class BaseDriverExecutorImpl {

    protected final DriverServices driverServices;
    protected final Logger logger = Logger.getLogger(getClass().getName());

    protected BaseDriverExecutorImpl(DriverServices driverservices) {
        this.driverServices = driverservices;
    }

    protected void waitForAngular() {
        driverServices.setScriptTimeout(40);
        ((JavascriptExecutor) driverServices.getDriver()).executeAsyncScript("var callback = arguments[arguments.length - 1];" +
                "angular.element(document.body).injector().get('$browser').notifyWhenNoOutstandingRequests(callback);");
    }

    protected void assertUnhandledExceptions() {
        driverServices.setScriptTimeout(40);
        String unhandledException = (String) ((JavascriptExecutor) driverServices.getDriver()).executeScript("var unhandledException = '';" +
                "if (window.automation) {" +
                "unhandledException = window.automation;" +
                "delete window.automation;" +
                "}" +
                "return unhandledException;"
        );
        if (unhandledException != null && unhandledException.length() > 0) {
            throw new MaasUIAutomationException("Unhandled application exception '" + unhandledException + "'. See browser console log for details");
        }
    }

    protected String getPendingHTTPRequests() {
        return (String) ((JavascriptExecutor) driverServices.getDriver()).executeAsyncScript("return JSON.stringify(angular.element('body').injector().get('$http').pendingRequests);");
    }

    /**
     * Generic interface for Inversion of Control pattern to execute actions
     */
    protected interface ActionExecutor {
        boolean execute(By by, ExceptionHolder failedEx);
    }

    protected void executeAction(Element element, final EventType eventType, final ActionExecutor executor) {
        final By by = driverServices.getBy(element);
        logger.info("Executing " + eventType.value() + " " + by.toString());
        final Map<String, String> messageDetails = new HashMap<>();
        final Date startTime = new Date();
        messageDetails.put("Locator", by.toString());
        messageDetails.put("XPATH", driverServices.getXpath(element));
        final String message = translateElement(element);
        final ExceptionHolder failedEx = new ExceptionHolder();
        final TestEvent testEvent = new TestEvent(eventType, message, messageDetails);
        notifyBeforeEvent(testEvent);
        try {
            if (driverServices.isAngularDefined()) {
                // Wait for angular to synchronize and complete outstanding $http and $timeout requests
                try {
                    waitForAngular();
                } catch (Exception e) {
                    // Rethrow a more specific exception for clarity in the test report
                    final MaasUIAutomationException waitForAngularException = new MaasUIAutomationException("waitForAngular failed to synchronize. See log. Pending HTTP requests are: " + getPendingHTTPRequests(), e);
                    failedEx.setInner(waitForAngularException);
                    throw waitForAngularException;
                }
                try {
                    final TestProperties testProperties = ApplicationContextHolder.getApplicationContext().getBean(TestProperties.class);
                    if (testProperties.failOnUnhandledExceptions()) {
                        assertUnhandledExceptions();
                    }
                } catch (Exception e) {
                    failedEx.setInner(e);
                    throw e;
                }
            }
            driverServices.getFluentWait().until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver webDriver) {
                    try {
                        boolean isPass = executor.execute(by, failedEx);
                        if (isPass) {
                            setEventTotalTime(messageDetails, startTime);
                            notifyAfterEvent(testEvent);
                        }
                        return isPass;
                    } catch (Exception ex) {
                        failedEx.setInner(ex);
                        return false;
                    }
                }
            });
        } catch (Exception ex) {
            // to find the right failure we execute again the method once with no wait time if the inner exception was not NoSuchElementException
            if (failedEx.getInner() != null && !failedEx.getInner().getClass().equals(org.openqa.selenium.NoSuchElementException.class)) {
                setFailureEventMap(messageDetails, startTime, eventType,failedEx );
                notifyAfterEvent(testEvent);
                throw new RuntimeException("Failed to execute event '" + eventType.value() + "'", failedEx.getInner());
            }
            try {
                By failBy = driverServices.getFailBy(element);
                boolean lastTryResult = executor.execute(failBy, failedEx);
                if (lastTryResult == false) { // usually if we got here it should fail
                    throw new MaasUIAutomationException("Failed to execute event '" + eventType.value() + "'", failedEx.getInner());
                }
                setEventTotalTime(messageDetails, startTime);
                notifyAfterEvent(testEvent);
            } catch (Exception ex1) {
                failedEx.setInner(ex1);
                setFailureEventMap(messageDetails, startTime, eventType,failedEx);
                notifyAfterEvent(testEvent);
                throw new RuntimeException("Failed to execute event '" + eventType.value() + "'", failedEx.getInner());
            }
        }
    }

    private void setFailureEventMap(Map<String, String> map, Date startTime,EventType eventType, ExceptionHolder exceptionHolder) {
        setEventTotalTime(map, startTime);
        map.put(TestEvent.RESOLUTION, driverServices.getScreenResolution());
        map.put(TestEvent.IS_FAILED, Boolean.TRUE.toString());
        map.put(TestEvent.EXC_OCCURS,"Failed to execute event: <b>" + eventType.value() + "</b>");
        map.put(TestEvent.EXC_CAUSE,exceptionHolder.getInner().getMessage());
        map.put(TestEvent.EXC_STACK_TRACE,prettifyExceptionHTML(exceptionHolder.getInner()));

    }

    private void setEventTotalTime(Map<String, String> map, Date startTime) {
        Date endTime = new Date();
        Long time = (endTime.getTime() - startTime.getTime()) / 1000;
        map.put(TestEvent.TOTAL_TIME, time.toString() + " Seconds");
    }

    private String prettifyExceptionHTML(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        String stackTrace = sw.toString();
        return "<br>" + stackTrace.replace(System.getProperty("line.separator"), "<br/>\n").replace("\tat","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at");
    }

    protected void notifyBeforeEvent(TestEvent ev) {
        Map<String, TestEventListener> testEventListenerMap = ApplicationContextHolder.getApplicationContext().getBeansOfType(TestEventListener.class);
        for (TestEventListener listener : testEventListenerMap.values()) {
            listener.beforeEvent(ev);
        }
    }

    protected void notifyAfterEvent(TestEvent ev) {
        Map<String, TestEventListener> testEventListenerMap = ApplicationContextHolder.getApplicationContext().getBeansOfType(TestEventListener.class);
        for (TestEventListener listener : testEventListenerMap.values()) {
            listener.afterEvent(ev);
        }
    }

    protected String translateElement(Element element) {

        Map<String, ElementTranslator> testEventListenerMap = ApplicationContextHolder.getApplicationContext().getBeansOfType(ElementTranslator.class);
        Element current = element;
        StringBuilder sb = new StringBuilder();
        if (current.equals(RootElement.getInstance())) {
            return "Body";
        }
        else {
            while (current != null && !current.equals(RootElement.getInstance())) {
                String exp = null;
                for (ElementTranslator elementTranslator : testEventListenerMap.values()) {
                    exp = elementTranslator.translate(current);
                    if (exp != null) {
                        exp = exp.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
                        exp = WordUtils.capitalizeFully(exp);
                        break;
                    }
                }
                if (exp == null) {
                    exp = current.getClass().getSimpleName();
                    if (exp.endsWith("Impl")) {
                        exp = exp.substring(0, exp.length() - 4);
                        exp = exp.replaceAll("(\\p{Ll})(\\p{Lu})", "$1 $2");
                    }
                    if (exp.equals("BaseElement")) {
                        exp = current.getLocator().toString();
                    }
                }


                exp = "<span title=\"" + current.getLocator().getType() + ": " + current.getLocator().getExpression() + "\">'" + exp + "'</span>";
                sb.append(exp).append(" in ");
                current = current.getParent();
            }

            return sb.toString().substring(0, sb.toString().length() - 4);
        }
    }

    /**
     * Causes the current thread to sleep for the given duration.
     * IMPORTANT: Take extra care when using this method since it impacts overall test execution time
     *
     * @param milliseconds milliseconds ot wait
     */
    protected void sleep(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getText(WebElement element) {
        if (!element.isDisplayed()) {
            logger.warning("Element '" + element.getTagName() + "' not displayed (by Selenium)! Going to check if it is inside SVG...");
            List<WebElement> elements = element.findElements(By.xpath("ancestor::*[name()='svg']"));
            if (elements.size() > 0) {
                logger.info("Getting text from element by JavaScript");
                String htmlScript = "return arguments[0].innerText";
                String svgScript = "return arguments[0].textContent";
                return (driverServices.executeJavaScript(htmlScript, element) == null) ? (String) driverServices.executeJavaScript(svgScript, element) : (String) driverServices.executeJavaScript(htmlScript, element);
            }

        }
        if (element.getTagName().equalsIgnoreCase("input") || element.getTagName().equalsIgnoreCase("textarea")) {
            // Input elements text is expressed by their input attribute
            return element.getAttribute("value");
        } else {
            return element.getText();
        }
    }

    public String getTagName(WebElement element) {
        if (!element.isDisplayed()) {
            logger.warning("Element '" + element.getTagName() + "' not displayed (by Selenium)! Going to check if it is inside SVG...");
            List<WebElement> elements = element.findElements(By.xpath("ancestor::*[name()='svg']"));
            if (elements.size() > 0) {
                logger.info("Getting tag name from element by JavaScript");
                String script = "return arguments[0].tagName";
                return (String) driverServices.executeJavaScript(script, element);
            }

        }
        return element.getTagName();
    }
}

