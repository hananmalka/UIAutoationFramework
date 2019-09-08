package com.ui.automation.selenium.exception;

/**
 * Checked exception denoting an error in the browser console log
 */
public class BrowserConsoleException extends Exception {

    /**
     * See {@link Exception#Exception(String)}
     */
    public BrowserConsoleException(String message) {
        super(message);
    }
}
