package com.ui.automation.selenium.wd;

/**
 * Exception to be used in anonymous classes used by Selenium wait mechanism
 */
public class ExceptionHolder extends Exception {
    private Exception inner;

    /**
     * Set the exception underlying this class
     * @param e
     */
    public void setInner(Exception e) {
        this.inner = e;
    }

    /**
     * Get the inner exception
     * @return
     */
    public Exception getInner() {
        return inner;
    }
}
