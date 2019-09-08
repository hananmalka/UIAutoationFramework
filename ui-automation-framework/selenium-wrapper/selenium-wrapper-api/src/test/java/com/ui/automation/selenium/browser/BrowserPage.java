package com.ui.automation.selenium.browser;

import com.ui.automation.common.element.items.SpecialKeys;

import java.nio.file.Path;

public interface BrowserPage {
    /**
     * Opens a new browser instance.
     *
     * Throws an unclassified runtime exception if a browser is already open
     *
     * @param browserType type of browser to open
     */
    void open(String browserType);

    /**
     * Quits all open browser sessions.
     *
     * Throws an unclassified runtime exception if a browser is already closed
     */
    void quit();

    /**
     * Closes the current (focused) browser instance.
     *
     * Throws an unclassified runtime exception if a browser is already closed
     */
    void close();

    /**
     * Navigates to given url
     * @param url Url to navigate to
     */
    // deprecated as not efficient
//    void get(String url);

    /**
     * Saves the source of the last loaded page to a file under target folder
     * @param targetDir target directory for saving the file
     * @param fileName file name
     */
    void savePageSource(Path targetDir, String fileName);

    /**
     * Saves the browser console to a file under target folder
     * @param targetDir target directory for saving the file
     * @param fileName file name
     */
    void saveConsoleLog(Path targetDir, String fileName);

    /**
     * Saves a png screenshot of the browser to target/screenshots folder
     * @param targetDir target directory for saving the file
     * @param fileName file name
     */
    void saveScreenshot(Path targetDir, String fileName);

    /**
     * Maximizes the current window if it is not already maximized
     */
    void maximize();

    /**
     * @return returns String Chrome or Firefox or InternetExplorer according to browser used
     */
    String getBrowserName();

    /**
     * Returns the browser's current URL
     * @return the current page URL
     */
    String getCurrentURL();

    /**
     * Browser back action
     */
    void back();

    /**
     * Return the open native browser alert. Throws an exception if no alert is displayed
     * @return
     */
    AlertPage alert();

    /**
     * Write to console
     */
    void consoleLog(String text);

    /**
     * Refresh the browser
     */
    void refresh();

    void startProfiling();
    void endProfiling();


    boolean isAtTopOfScroll();
//    void navigateToAppUrl(String appUrl);

    int getTimeZoneOffset();

    boolean isDateInDst(int year, int month, int day, int hour);


    void pressKeyboard(SpecialKeys key);

    /**
     * Returns the browser’s current time
     */
    long getTime();

    void closeCurrentBrowserTab();
}
