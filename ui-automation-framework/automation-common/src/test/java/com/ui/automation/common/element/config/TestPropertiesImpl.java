package com.ui.automation.common.element.config;

// TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//import com.automation.common.utils.LocaleUtils;
//import com.maas.automation.environment.configuration.UserDetailsManager;
//import com.maas.automation.tools.tenantManagement.TenantStore;
//import com.maas.automation.tools.test.data.i18n.MultiLanguageSupportedType;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: peere
 * Date: 07/01/13
 * Time: 14:52
 * To change this template use File | Settings | File Templates.
 */
@Component
public class TestPropertiesImpl implements TestProperties {

    private static final java.lang.String IS_WINDOWS_BASED_HOST_PROPERTY = "windowsBasedHost";
    private static final Boolean IS_WINDOWS_BASED_HOST_DEFAULT_VALUE = false;
    private Properties properties = new Properties();
    public static final String PROTOCOL = "protocol";
    public static final String CLIENT_HOST = "clientHost";
    public static final String CLIENT_PORT = "clientPort";
    public static final String QUERY_PARAMETERS = "queryParameters";
    public static final String LOGIN_USER_EMAIL = "loginUserEmail";
    public static final String LOGIN_USER_NAME = "loginUserName";
    public static final String LOGIN_PASSWORD = "loginPassword";
    public static final String TENANTID_QUERY_PARAMETER = "TENANTID=";
    public static final String BROWSER_TYPE = "BROWSER_TYPE";
    public static final String DEFAULT_BROWSER_TYPE = "chrome";
    //    public static final String DEFAULT_BROWSER_TYPE = "firefox";
    private static final String AUTO_SCREENSHOT = "reportScreenshot";
    private static final String DEFAULT_AUTO_SCREENSHOT = "false";
    private static final String AUTO_OPEN_REPORT_PROPERTY = "reportAutoOpen";
    private static final Boolean AUTO_OPEN_REPORT_VALUE = true;
    //    private static final Boolean AUTO_OPEN_REPORT_VALUE = false;
    private static final String CREATE_MAIN_REPORT_PROPERTY = "reportCreateMain";
    private static final Boolean CREATE_MAIN_REPORT_VALUE = false;
    private static final String IS_PERFORMANCE_TEST_PROPERTY = "maas.performance";
    private static final Boolean IS_PERFORMANCE_TEST_VALUE = false; //has screenshot related stuff
    private static final String IS_DOCKER_PROPERTY = "docker";
    private static final Boolean IS_DOCKER_DEFAULT_VALUE = true;
    private static final String CHROME_OPTIONS_PROPERTY = "chromeOptions";
    private static final String CHROME_OPTIONS_VALUE = "--incognito,--disable-extensions,start-fullscreen,--enable-memory-info,--test-type";
    private static final String CHROME_PROXY_PAC_PROPERTY = "chromePac";
    private static final String CHROME_DRIVER_FULL_PATH_PROPERTY = "chromeDriverPath";
    private static final String CHROME_DRIVER_FULL_PATH_VALUE = "";
    private static final String CHROME_DRIVER_EXECUTABLE_NAME_PROPERTY = "chromeDriverExecutableName";
    //    private static final String CHROME_DRIVER_EXECUTABLE_NAME_VALUE = "chromedriver.exe";
    private static final String CHROME_DRIVER_EXECUTABLE_NAME_VALUE = "chromedriver";
    private static final String REMOTE_WEB_DRIVER_URL_PROPERTY = "remoteWebDriverUrl";
    private static final String BROWSER_VERSION_PROPERTY = "browser.version";
    private static final String BROWSER_VERSION_VALUE = "";
    private static final String DEFAULT_SELENIUM_TIMEOUT_SECONDS = "40";
    private static final Boolean AUTO_RECORDING_VALUE = true;
    private static final String AUTO_RECORDING_PROPERTY = "autoRecording";
    private static final String FAIL_RETRY_COUNT = "failRetryCount";
    private static final String FAIL_RETRY_COUNT_DEFAULT_VALUE = "0";
    private static final String FAIL_ON_UNHANDLED_EXCEPTIONS = "failOnUnhandledException";
    private static final Boolean FAIL_ON_UNHANDLED_EXCEPTIONS_DEFAULT_VALUE = Boolean.TRUE;
    private static final String BROWSER_PROXY_SERVER = "browserProxyServer";
    private static final String BROWSER_PROXY_SERVER_DEFAULT_VALUE = "http://web-proxy.bbn.hp.com:8080";
    private static final String BROWSER_WINDOW_SIZE = "browserWindowSize";
    private static final String BROWSER_WINDOW_SIZE_DEFAULT_VALUE = "1680,1050";
    private static final String UI_LOOKUP_PROPERTY_FILE_NAME = "ui";
    private static final String UI_LOOKUP_HOST = "host";
    private static final String UI_LOOKUP_PORT = "port";
    private static final String UI_LOOKUP_URL = "url";
    private static final String LOCALHOST = "localhost";
    private static final String IS_VISUAL_TEST = "isVisualTest";
    private static final String IS_VISUAL_TEST_DEFAULT_VALUE = "false";

    protected TestPropertiesImpl() {
        try {
            // Read property file
            InputStream resourceAsStream = this.getClass().getResourceAsStream("selenium.properties");
            properties.load(resourceAsStream);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * Get the default browser version for use on the Remote system.
     *
     * @return "3.6" or whatever you have specified on system property 'browser.version'
     */
    @Override
    public String getBrowserVersion() {
        return System.getProperty(BROWSER_VERSION_PROPERTY, BROWSER_VERSION_VALUE);
    }


    @Override
    public String getRemoteDriverURL() {
        return System.getProperty(REMOTE_WEB_DRIVER_URL_PROPERTY);
    }

    @Override
    public String chromeDriverExecutableName() {
        return System.getProperty(CHROME_DRIVER_EXECUTABLE_NAME_PROPERTY, CHROME_DRIVER_EXECUTABLE_NAME_VALUE);
    }

    @Override
    public String getChromeDriverFullPath() {
        return System.getProperty(CHROME_DRIVER_FULL_PATH_PROPERTY, CHROME_DRIVER_FULL_PATH_VALUE);
    }

    @Override
    public String getChromePacFilePath() {
        return System.getProperty(CHROME_PROXY_PAC_PROPERTY);
    }

    @Override
    public String getChromeOptions() {
        return System.getProperty(CHROME_OPTIONS_PROPERTY, CHROME_OPTIONS_VALUE);
    }

    @Override
    public boolean isPerformanceTest() {
        String maasPerformance = System.getProperty(IS_PERFORMANCE_TEST_PROPERTY, IS_PERFORMANCE_TEST_VALUE.toString());
        return Boolean.parseBoolean(maasPerformance);
    }

    @Override
    public boolean isDockerSeleniumGrid() {
        String isDocker = System.getProperty(IS_DOCKER_PROPERTY, IS_DOCKER_DEFAULT_VALUE.toString());
        return Boolean.parseBoolean(isDocker);
    }

    @Override
    public String getPerformancePropertyName() {
        return IS_PERFORMANCE_TEST_PROPERTY;
    }

    @Override
    public Boolean getIsCreateFullReportHTML() {
        return Boolean.parseBoolean(System.getProperty(CREATE_MAIN_REPORT_PROPERTY, CREATE_MAIN_REPORT_VALUE.toString()));
    }

    @Override
    public Boolean getIsAutoOpenReport() {
        return Boolean.parseBoolean(System.getProperty(AUTO_OPEN_REPORT_PROPERTY, AUTO_OPEN_REPORT_VALUE.toString()));
    }


    @Override
    public Boolean getIsAutoScreenshot() {
        return Boolean.parseBoolean(System.getProperty(AUTO_SCREENSHOT, DEFAULT_AUTO_SCREENSHOT));
    }

    /**
     * Get the browser type from System.getProperty
     * If not defined the default is chrome
     *
     * @return chrome/firefox/iexplorer
     */
    @Override
    public String getBrowserType() {
        return System.getProperty(BROWSER_TYPE, DEFAULT_BROWSER_TYPE);
    }

    /**
     * Return the predefined default browser type
     *
     * @return
     */
    @Override
    public String getDefaultBrowserType() {
        return DEFAULT_BROWSER_TYPE;
    }


    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getTenantID() {
//        String tenantId = null;
//        if (!ContextHolder.isUserContextEmpty()) {
//            tenantId = ContextHolder.getCurrentUserContext().getTenantId();
//        }
//        if (tenantId == null) {
//            tenantId = TenantStore.getInstance().getDefaultTenantDetails().getTenantId();// UserDetailsManager.getDefaultTenantId();
//        }
//        return tenantId;
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getTenantIdQueryParameter() {
//        String tenantId = removeLeading(getTenantID(), "/");
//        return TENANTID_QUERY_PARAMETER + tenantId;
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getLoginUserEmail() {
//        //will return devUser1@hp.com
//        String userEmail = System.getProperty(LOGIN_USER_EMAIL);
//        if (userEmail == null) {
//
//            userEmail = TenantStore.getInstance().getTenantDetails(this.getTenantID()).getAdminUserName();
//        }
//        return userEmail;
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getLoginUserName() {
//        String loginUserName = System.getProperty(LOGIN_USER_NAME);
//        if (loginUserName == null) {
//            // Login user id is used as the user's first and last names, see UserRepository.java
//            loginUserName = UserDetailsManager.getDefaultAdminUserId() + ", " + UserDetailsManager.getDefaultAdminUserId();
//        }
//        return loginUserName;
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getLoginPassword() {
//        String loginPassword = System.getProperty(LOGIN_PASSWORD);
//        if (loginPassword == null) {
//            loginPassword = TenantStore.getInstance().getTenantDetails(this.getTenantID()).getAdminPassword();
//        }
//        return loginPassword;
//    }

    @Override
    public String getQueryParameters() {
        return System.getProperty(QUERY_PARAMETERS, properties.getProperty(QUERY_PARAMETERS));
    }

//    @Override
//    public String getAppBaseUrl() {
//        final String clientPort = getClientPort();
//        String port = "";
//        if (clientPort != null && clientPort.length() > 0) {
//            port = ":" + clientPort;
//        }
//        return getProtocol() + "://" + getClientHost() + port;
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getAppUrl() {
//        String appBaseUrl = removeTrailing(getAppBaseUrl(), "/");
//        String queryParameters = removeLeading(getQueryParameters(), "&");
//        return appBaseUrl + '?' + getTenantIdQueryParameter() + '&' + queryParameters;
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public Locale getTestLocaleJavaFormat() {
//        return LocaleUtils.getLocale();
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getTestLocaleServerFormatString() {
//        return LocaleUtils.getTestLocaleServerFormat();
//    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public MultiLanguageSupportedType getCurrentUserLangType() {
//        for (MultiLanguageSupportedType lang : MultiLanguageSupportedType.values()) {
//            if (lang.getCode().equals(this.getTestLocaleUiFormatString())) {
//                return lang;
//            }
//        }
//        return MultiLanguageSupportedType.ENGLISH;
//    }


    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    @Override
//    public String getTestLocaleUiFormatString() {
//        Locale locale = LocaleUtils.getLocale();
//        return locale.toString().replace("_", "-");
//    }

    @Override
    public int getDriverTimeout() {
        return Integer.parseInt(System.getProperty("selenium-timeout", DEFAULT_SELENIUM_TIMEOUT_SECONDS));
    }


    @Override
    public Boolean isAutoRecording() {
        return Boolean.parseBoolean(System.getProperty(AUTO_RECORDING_PROPERTY, AUTO_RECORDING_VALUE.toString()));
    }

    @Override
    public int getRetryCount() {
        return Integer.parseInt(System.getProperty(FAIL_RETRY_COUNT, FAIL_RETRY_COUNT_DEFAULT_VALUE));
    }

    @Override
    public boolean failOnUnhandledExceptions() {
        return Boolean.parseBoolean(System.getProperty(FAIL_ON_UNHANDLED_EXCEPTIONS, FAIL_ON_UNHANDLED_EXCEPTIONS_DEFAULT_VALUE.toString()));
    }

    @Override
    public String getBrowserProxyServer() {
        return System.getProperty(BROWSER_PROXY_SERVER, BROWSER_PROXY_SERVER_DEFAULT_VALUE);
    }

    @Override
    public String getBrowserWindowSize() {
        return System.getProperty(BROWSER_WINDOW_SIZE, BROWSER_WINDOW_SIZE_DEFAULT_VALUE);
    }

    @Override
    public boolean isVisualTest() {
        return Boolean.parseBoolean(System.getProperty(IS_VISUAL_TEST, IS_VISUAL_TEST_DEFAULT_VALUE.toString()));
    }

    @Override
    public boolean isWindowsBasedHost() {
        String windowsHost = System.getProperty(IS_WINDOWS_BASED_HOST_PROPERTY, IS_WINDOWS_BASED_HOST_DEFAULT_VALUE.toString());
        return Boolean.parseBoolean(windowsHost);
    }

    /**
     * Remove trailing text if it exists in src string
     *
     * @param src
     * @param textToRemove
     * @return
     */
    private String removeTrailing(String src, String textToRemove) {
        if (src.endsWith(textToRemove)) {
            return src.substring(0, src.length() - textToRemove.length());
        }
        return src;
    }

    /**
     * Remove leading text if it exists in src string
     *
     * @param src
     * @param textToRemove
     * @return
     */
    private String removeLeading(String src, String textToRemove) {
        if (src.startsWith(textToRemove)) {
            return src.substring(textToRemove.length());
        }
        return src;
    }

}
