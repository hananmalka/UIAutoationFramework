package com.ui.automation.selenium.wd;

import java.nio.file.Path;

/**
 * The class provides general browser operations and details
 */
public interface Browser {

    /**
     * Opens a new browser instance.
     *
     * @param browserType type of browser to open
     * @throws Exception of subtype {@link java.lang.RuntimeException} if a browser is already open
     */
    void open(String browserType);

    /**
     * Quits this driver, closing every associated window.
     *
     * @throws Exception of subtype {@link java.lang.RuntimeException} if a browser is already closed
     */
    void quit();

    /**
     * Quits this driver, closing every associated window.
     *
     * @throws Exception of subtype {@link java.lang.RuntimeException} if a browser is already closed
     */
    void close();

    /**
     * Navigates to given url
     *
     * @param url Url to navigate to
     */
    void get(String url);

    /**
     * Navigates to given app url with default host:port , tenantId and default Params.<p>
     * ie: navigateToAppUrl("/saw/sacm/");
     * </p>
     *
     * @param appUrl Url to navigate to
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    void navigateToAppUrl(String appUrl);

    /**
     * Save the source of the last loaded element to a file.
     *
     * @param targetDir target directory for saving the file
     * @param fileName  file name
     */
    void savePageSource(Path targetDir, String fileName);

    /**
     * Save the browser console log to a file
     *
     * @param targetDir target directory for saving the file
     * @param fileName  file name
     */
    void saveConsoleLog(Path targetDir, String fileName);

    /**
     * Get the browser console log to a string
     */
    String getConsoleLog();

    /**
     * Saves a png screenshot of the browser to target/screenshots folder
     *
     * @param targetDir target directory for saving the file
     * @param name      file name
     */
    void saveScreenshot(Path targetDir, String name);

    /**
     * Maximizes the current window if it is not already maximized
     */
    void maximize();


    /**
     * Send keyboard key to the current active (focused) element
     *
     * @param key
     */
    void pressKey(CharSequence key);

    /**
     * @return returns String Chrome or Firefox or InternetExplorer according to browser used
     */
    String getBrowserName();

    /**
     * @return returns the href attribute of the base tag, e.g. <code><base href="/v2/"></code> or an empty string if it is not defined.
     */
    String getBaseHref();

    /**
     * Browser back action
     */
    void back();

    /**
     * Refresh the browser
     */
    void refresh();

    /**
     * Returns the browser's current URL
     *
     * @return the current element URL
     */
    String getCurrentURL();

    /**
     * Return the open native browser alert. Throws an exception if no alert is displayed
     *
     * @return
     */
    Alert alert();

    Profiling profiling();

    boolean isAtTopOfScroll();

    int getTimeZoneOffset();

    boolean isDateInDst(int year, int month, int day, int hour);

    /**
     * Switch to an internal iframe by its id.
     * <p/>
     * All of the following actions are executed in the context of this frame
     * until calling this method again with another frame or calling
     * {@link #switchToTopFrame()}.
     * <p/>
     * Don't forget to call {@link #switchToTopFrame()} after completing work in iframe context.
     * <p/>
     * Sample usage:
     * <code>
     * try {
     * browser.switchToFrame("my-frame-id");
     * // Actions and verifications here
     * }
     * catch (...) {
     * // Optionally handle exception
     * }
     * finally {
     * browser.switchToTopFrame();
     * }
     * </code>
     *
     * @param frameId frame id to switch to.
     */
    void switchToFrame(String frameId);

    /**
     * Switch back to top level frame. All following actions will be executed in this context.
     */
    void switchToTopFrame();

    /**
     * Assumes that the browser has only 2 tabs.
     * Switches from the current tab to the other one.
     * All following test actions will be executed in the context of the new tab.
     */
    void switchToTab();

    /**
     * Moves the mouse from its current position to the screen's top left corner
     */
    void moveMouseToTopLeft();

    /**
     * Returns the browser’s current time
     */
    long getTime();

    void closeCurrentTab();

    void openNewTab();

    /**
     * Returns the browser’s current tab title.
     * Note that the title is of the tab in context which isn't necessarily the displayed tab
     */
    String getTitle();
}
