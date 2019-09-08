package com.ui.automation.selenium.wd;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.locator.ElementLocatorService;
import com.ui.automation.selenium.service.LocatorService;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Common MaaS driver services
 */
public class DriverServices {
    private final ElementLocatorService elementLocatorService;
    private final LocatorService locatorService;
    private WebDriver driver;
    private Visual visual;
    private final int driverTimeoutSeconds;

    public DriverServices(int driverTimeoutSeconds) {
        this.locatorService = ApplicationContextHolder.getApplicationContext().getBean(LocatorService.class);
        this.elementLocatorService = ApplicationContextHolder.getApplicationContext().getBean(ElementLocatorService.class);
        this.driverTimeoutSeconds = driverTimeoutSeconds;
    }

    public void setScriptTimeout(int timeout){
        driver.manage().timeouts().setScriptTimeout(timeout, TimeUnit.SECONDS);
    }

    public boolean isAngularDefined(){
        try{
            return (Boolean)this.executeJavaScript("return window.angular !== undefined && angular.element(document.body).injector() !== undefined");
        }
        catch (Exception ex){
            return false;
        }
    }

    public boolean isJQueryDefined(){
        try{
            return (Boolean)this.executeJavaScript("return !!window.jQuery && window.jQuery.active == 0");
        }
        catch (Exception ex){
            return false;
        }
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    void setVisual(Visual visual) {
        this.visual = visual;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public By getBy(Element element) {
        final List<Locator> locators = elementLocatorService.getLocators(element);
        return locatorService.getByLocators(locators, (JavascriptExecutor) driver);
    }


    public String getXpath(Element element) {

        final List<Locator> locators = elementLocatorService.getLocators(element);
        return locatorService.getXpath(locators);

    }
    public By getFailBy(Element element) {
        final List<Locator> locators = elementLocatorService.getLocators(element);
        return locatorService.getByFailedLocators(locators);
    }

    public String getScreenResolution(){
        return this.driver.manage().window().getSize().getWidth() + "x" + this.driver.manage().window().getSize().getHeight();
    }

    public Object executeJavaScript(String script, Object... args) {
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    public void scrollToView(WebElement webElement) {
        this.executeJavaScript("arguments[0].scrollIntoView(true);", webElement);
    }

    /**
     * Returns a Selenium Wait object
     *
     * @return
     */
    public Wait<WebDriver> getFluentWait() {
        return new FluentWait<WebDriver>(this.driver)
                .withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(StaleElementReferenceException.class)
                .ignoring(NoSuchElementException.class)
                .ignoring(ElementNotVisibleException.class)
                .ignoring(MaasUIAutomationException.class);
//        return new FluentWait<>(this.driver)
////                .withTimeout(driverTimeoutSeconds, TimeUnit.SECONDS)
////                .pollingEvery(500, TimeUnit.MILLISECONDS)
//                .ignoring(StaleElementReferenceException.class)
//                .ignoring(NoSuchElementException.class)
//                .ignoring(ElementNotVisibleException.class)
//                .ignoring(MaasUIAutomationException.class);
    }

	public boolean isAtTopOfScroll(By by) {
		return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop === 0", driver.findElement(by));
	}

    public String getElementHTML(WebElement element){
        return (String)((JavascriptExecutor)driver).executeScript("return arguments[0].outerHTML;", element);
    }

    public Visual getVisual() {
        return visual;
    }
}
