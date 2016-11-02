package com.ui.automation.common.element.config;


// TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//import com.maas.automation.tools.test.data.i18n.MultiLanguageSupportedType;

/**
 * Created with IntelliJ IDEA.
 * User: peere
 * Date: 21/01/14
 * Time: 13:30
 * To change this template use File | Settings | File Templates.
 */
public interface TestProperties {


    /**
     * Returns the env variable for "{@value TestPropertiesImpl#BROWSER_VERSION_PROPERTY}".<br/>
     * The default is: "{@value TestPropertiesImpl#BROWSER_VERSION_VALUE}".<br/>
     * Get the default browser version for use on the Remote system.
     * @return
     */
    public String getBrowserVersion();

    /**
     * Returns the env variable for "{@value TestPropertiesImpl#REMOTE_WEB_DRIVER_URL_PROPERTY}".<br/>
     * If not exist return null
     * @return
     */
    String getRemoteDriverURL();
    /**
     * Returns the env variable for "{@value TestPropertiesImpl#CHROME_DRIVER_EXECUTABLE_NAME_PROPERTY}".<br/>
     * The default is: "{@value TestPropertiesImpl#CHROME_DRIVER_EXECUTABLE_NAME_VALUE}".
     * @return
     */
    String chromeDriverExecutableName();
    /**
     * Returns the env variable for "{@value TestPropertiesImpl#CHROME_DRIVER_FULL_PATH_PROPERTY}".<br/>
     * The default is: "{@value TestPropertiesImpl#CHROME_DRIVER_FULL_PATH_VALUE}".
     * @return
     */
    String getChromeDriverFullPath();
    /**
     * Returns the env variable for "{@value TestPropertiesImpl#CHROME_PROXY_PAC_PROPERTY}".<br/>
     * If not exist return null
     * @return
     */
    String getChromePacFilePath();
    /**
     * Returns the env variable for "{@value TestPropertiesImpl#CHROME_OPTIONS_PROPERTY}".<br/>
     * The default is: "{@value TestPropertiesImpl#CHROME_OPTIONS_VALUE}".
     * @return
     */
    String getChromeOptions();
    /**
     * Returns the env variable for "{@value TestPropertiesImpl#IS_PERFORMANCE_TEST_PROPERTY}".<br/>
     * The default is "{@value TestPropertiesImpl#IS_PERFORMANCE_TEST_VALUE}".
     * If set to true it will create performance web element test result
     * @return
     */
    boolean isPerformanceTest();

    /**
     * Returns true if tests are running on a docker-based Selenium grid, false otherwise.
     * Default value is <code>false.</code>
     * @return
     */
    boolean isDockerSeleniumGrid();
    /**
     * Returns the only value of the property "{@value TestPropertiesImpl#IS_PERFORMANCE_TEST_PROPERTY}".<br/>
     *
     * @return {@value TestPropertiesImpl#IS_PERFORMANCE_TEST_PROPERTY}
     */
    String getPerformancePropertyName();

    /**
     * Returns the env variable for "reportCreateMain". The default is false
     * If set to true it will create main html report that contains all class that run using the test.
     * @return
     */
     Boolean getIsCreateFullReportHTML();

    /**
     * Returns the evn variable for "reportAutoOpen". Default is true
     * If set to false it will not open the report in browser after ui
     * @return
     */
    Boolean getIsAutoOpenReport();

    /**
     * Return the env variable of "reportScreenshot", the default is false.
     * If it set to true, the reporter will take screenshot on each action and expect
     * @return
     */
    Boolean getIsAutoScreenshot();

    /**
     * Returns the evn variable for "BROWSER_TYPE", if doesn't exist it will return the default browser type
     * @return
     */
    String getBrowserType();
    /**
     * Return the Default Browser type. this will not call system properties
     * @return
     */
    String getDefaultBrowserType();

    /**
     * Returns the env variable "loginUserEmail". If it doesn't exist falls back to using the same parameter as defined in selenium.properties e.g. "adminUser@hp.com"
     *
     * @return
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getLoginUserEmail();

    /**
     * Returns the env variable "loginUserName". If it doesn't exist falls back to using the same parameter as defined in selenium.properties e.g. "name_adminUser"
     *
     * @return
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getLoginUserName();

    /**
     * Returns the env variable "loginPassword". If it doesn't exist falls back to using the same parameter as defined in selenium.properties e.g. "Password1"
     *
     * @return
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getLoginPassword();

    /**
     * Returns the env variable "protocol". If it doesn't exist falls back to using the same parameter as defined in selenium.properties e.g. "http"
     *
     * @return
     */
//    String getProtocol();

    /**
     * Returns the env variable "clientHost". If it doesn't exist falls back to using the same parameter as defined in selenium.properties e.g. "peere2.emea.hpqcorp.net"
     *
     * @return
     */
//    String getClientHost();

    /**
     * Returns the env variable "clientPort". If it doesn't exist falls back to using the same parameter as defined in selenium.properties, e.g. "8000"
     *
     * @return
     */
//    String getClientPort();

    /**
     * Returns the env variable "app_tenantId". If it doesn't exist falls back to using the same parameter as defined in selenium.properties, e.g. "mockTenant"
     *
     * @return
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getTenantID();

    /**
     * Returns a URL encoding of the tenant ID query parameter, i.e.  /?TENANTID=mockTenant
     *
     * @return
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getTenantIdQueryParameter();

    /**
     * Returns the env variable "query_parameters". If it doesn't exist falls back to using the same parameter as defined in selenium.properties, e.g. "showSampleApp=true"
     *
     * @return
     */
    String getQueryParameters();

    /**
     * Returns the app base url without tenant id of application, i.e. http://peere2.emea.hpqcorp.net:8000
     *
     * @return
     */
//    String getAppBaseUrl();

    /**
     * Returns the entire app url without an application name i.e. http://peere2.emea.hpqcorp.net:8000/?TENANTID=mockTenant&param1=val1
     *
     * @return
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getAppUrl();

    /**
     * get the locale the test should run with
     */
    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    Locale getTestLocaleJavaFormat();

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getTestLocaleServerFormatString();

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    String getTestLocaleUiFormatString();


    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
    //MultiLanguageSupportedType getCurrentUserLangType();
    /**
     * Returns the WebDriver timeout to use
     */
    int getDriverTimeout();


    /*
     * Automatically retrieve a recording in case of test failure
     */
    Boolean isAutoRecording();

    /**
     * Returns the number of retries to perform on failed tests
     * @return
     */
    int getRetryCount();

    /**
     * Should tests fail on unhandled exceptions in the application
     * @return
     */
    boolean failOnUnhandledExceptions();

    /**
     * Returns the proxy server for the browser in the form "http://web-proxy.bbn.hp.com:8080"
     * @return
     */
    String getBrowserProxyServer();

    /**
     * Returns the required browser window dimension with a comma separating between width and height in the form of "width,height"
     * e.g. "1680,1050"
     * @return
     */
    String getBrowserWindowSize();

    /**
     * Returns true if visual regression testing is expected, false otherwise
     * @return
     */
    boolean isVisualTest();

    /**
     * Returns true if required host for testing is windows based
     * Used to separate test runs between docker and windows.
     * @return
     */
    boolean isWindowsBasedHost();
}