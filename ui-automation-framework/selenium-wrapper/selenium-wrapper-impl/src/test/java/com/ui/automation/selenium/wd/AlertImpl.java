package com.ui.automation.selenium.wd;

import com.ui.automation.common.exception.MaasUIAutomationException;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by yechielt on 18/02/14.
 */
public class AlertImpl implements Alert {

    private final WebDriver driver;
    private final int timeoutSeconds;

    public AlertImpl(WebDriver driver, int timeoutSeconds) {
        this.driver = driver;
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public void accept() {
        waitForAlert().accept();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //ignore
        }
    }

    @Override
    public void dismiss() {
        waitForAlert().dismiss();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            //ignore
        }
    }

    @Override
    public String getText() {
        return waitForAlert().getText();
    }

    private org.openqa.selenium.Alert waitForAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutSeconds);
            return wait.ignoring(NoAlertPresentException.class).until(new AlertAvailable());
        } catch (Exception ex) {
            throw new MaasUIAutomationException("Could not find alert!");
        }
    }

    private static class AlertAvailable implements ExpectedCondition<org.openqa.selenium.Alert> {
        @Override
        public org.openqa.selenium.Alert apply(WebDriver driver) {
            return driver.switchTo().alert();
        }
    }

}
