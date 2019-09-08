package com.ui.automation.selenium.wd;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 12/01/14
 * Time: 10:50
 * To change this template use File | Settings | File Templates.
 */
public interface MaasDriver {

    /**
     * Returns the actions supported by the driver
     * @return
     */
    Actions actions();

    /**
     * Returns the expectation supported by the driver
     * @return
     */
    Expects expects();

    /**
     * Returns an instance of {@link Finds} which provides element location information
     * @return
     */
    Finds finds();

    /**
     * Get browser instance for interacting with the browser window
     * @return browser instance
     */
    Browser browser();

    SpecificElementExecuter specificElementExecuter();



    /**
     * Retrieves the remote selenium node's host
     * @return the DNS name of the selenium remote node, null if local run
     */
    String getRemoteSeleniumNodeHost();

    long getCurrentRemoteCreationTimeMS();

    /**
     * Retrieves the remote selenium node's port
     * @return the port of the selenium remote node, -1 if local run
     */
    int getRemoteSeleniumNodePort();
}
