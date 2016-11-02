package com.ui.automation.selenium.wd;

/**
 * Native browser alert
 */
public interface Alert {

    /**
     * Dismiss open alert
     */
    void dismiss();

    /**
     * Accept alert
     */
    void accept();

    /**
     * Returns the alert text for debugging purposes (when an alert is open Selenium
     * throws an UnhandledAlertException on almost any opertaion it tries to perform)
     * @return
     */
    String getText();
}
