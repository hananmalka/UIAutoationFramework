package com.ui.automation.selenium.wd;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: peere
 * Date: 18/02/14
 * Time: 12:13
 * To change this template use File | Settings | File Templates.
 */
public class FindsImpl extends BaseDriverExecutorImpl implements Finds {

    //private final DriverServices driverServices;

    public FindsImpl(DriverServices driverServices) {
        //this.driverServices = driverServices;
        super(driverServices);
    }

    @Override
    public int index(final Element nodeElement) {
        Element precedingSiblingsElement = new Element() {
            @Override
            public Locator getLocator() {
                return Locator.xpath("./preceding-sibling::*");
            }

            @Override
            public Element getParent() {
                return nodeElement;
            }

            @Override
            public Element expectVisible() {
                // it is special element, should never throw exception
                return this;
            }

            @Override
            public Element expectNotVisible() {
                // it is special element, should never throw exception
                return this;
            }

        };

        final By nodeElementBy = driverServices.getBy(nodeElement);
        final By precedingSiblingsBy = driverServices.getBy(precedingSiblingsElement);

        return driverServices.getFluentWait().until(new ExpectedCondition<Integer>() {
            @Override
            public Integer apply(org.openqa.selenium.WebDriver webDriver) {
                // First try to locate the node whose index we want.
                // This will throw if it didn't find the element
                WebElement nodeElement = webDriver.findElement(nodeElementBy);
                if (!nodeElement.isDisplayed()) {
                    return null;
                }

                // Now get the preceding elements
                final List<WebElement> precedingSiblingsElements = webDriver.findElements(precedingSiblingsBy);
                // Return their count (this is the node index)
                return precedingSiblingsElements.size();
            }
        });
    }

    @Override
    public String getText(Element element) {
        final By elementBy = driverServices.getBy(element);

        return driverServices.getFluentWait().until(x -> {
            WebElement webElement = driverServices.getDriver().findElement(elementBy);

            return getText(webElement);
        });
    }

    @Override
    public String getTagName(Element element) {
        final By elementBy = driverServices.getBy(element);

        return driverServices.getFluentWait().until(x -> {
            try {
                WebElement webElement = driverServices.getDriver().findElement(elementBy);
                return getTagName(webElement);
            }
            catch (Exception ex){
                return "unknown";
            }
        });
    }

}
