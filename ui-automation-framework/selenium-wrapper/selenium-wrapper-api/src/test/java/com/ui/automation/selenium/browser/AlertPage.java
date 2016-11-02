package com.ui.automation.selenium.browser;

/**
 * Browser native alert (such as the ones opened with alert('hello world') from JS)
 */
public interface AlertPage {

    /**
     * Dismiss open alert
     */
    void dismiss();

    /**
     * Accept alert
     */
    void accept();

    /**
     * Validate alert text
     * @param text
     */
    void expectToContainText(String text);

    /**
     * Returns the alert text for debugging purposes (when an alert is open Selenium
     * throws an UnhandledAlertException on almost any operation it tries to perform)
     * @return
     */
    String getText();
}
