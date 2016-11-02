package com.ui.automation.selenium.wd.specificElements;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.app.eventBus.EventType;
import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.elements.api.Element;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.BaseDriverExecutorImpl;
import com.ui.automation.selenium.wd.DriverServices;
import com.ui.automation.selenium.wd.MaasDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: yasterzo
 * Date: 15/06/14
 * Time: 17:33
 * To change this template use File | Settings | File Templates.
 */
public class VisibleAfterActionImpl extends BaseDriverExecutorImpl implements VisibleAfterAction {

    public VisibleAfterActionImpl(DriverServices driverservices) {
        super(driverservices);
    }


    /**
     * Method will implement click action on actionElement till expectedElement occur or timeout
     */
    @Override
    public boolean click(final Element actionElement, final Element expectedElement) {


        final TestEvent testEvent = notifyBeforeAction(new EventType(EventType.Message.ACTION_CLICK), actionElement);


        try {
            return driverServices.getFluentWait().until(new ExpectedCondition<Boolean>() {
                @Override
                public Boolean apply(WebDriver webDriver) {

                    getDriver().actions().click(actionElement);

                    final By by = driverServices.getBy(expectedElement);
                    WebElement element = driverServices.getDriver().findElement(by);
                    // If the element does not indicate any WebElement then it is considered as invisible
                    if (!element.isDisplayed()) {
                        // found a visible element
                        throw new MaasUIAutomationException("Given element doesn't displayed!!!");
                    }
                    if (element.getSize().getHeight() == 0) {
                        throw new MaasUIAutomationException("Given element height=0, so it is not visible!!!");
                    }
                    if (element.getSize().getWidth() == 0) {
                        throw new MaasUIAutomationException("Given element width=0, so it is not visible!!!");
                    }

                    // All element are visible
                    return true;
                }
            });
        } finally {
            notifyAfterEvent(testEvent);
        }
    }


    private TestEvent notifyBeforeAction(EventType eventType, Element element) {
        StringBuilder sb = new StringBuilder();
        final By by = driverServices.getBy(element);
        sb.append(by.toString()).append("<br/>");
        final Map<String, String> messageDetails = new HashMap<>();
        messageDetails.put("Locators", sb.toString());
        final TestEvent testEvent = new TestEvent(eventType, "Action " + eventType.value() + " on element", messageDetails);
        notifyBeforeEvent(testEvent);
        return testEvent;
    }


    protected MaasDriver getDriver() {
        // Get this thread's driver
        final DriverTestContext driverTestContext = ApplicationContextHolder.getApplicationContext().getBean(DriverTestContext.class);
        return driverTestContext.getDriver();
    }
}
