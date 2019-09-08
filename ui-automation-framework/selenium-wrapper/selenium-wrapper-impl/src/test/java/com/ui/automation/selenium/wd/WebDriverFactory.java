package com.ui.automation.selenium.wd;

import com.google.gson.GsonBuilder;
import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.common.junit.TestDetails;
import com.ui.automation.common.junit.TestDetailsHolder;
import com.ui.automation.common.element.config.TestProperties;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.json.JSONObject;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.*;
import org.openqa.selenium.safari.SafariDriver;

import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.TimeoutException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: peere
 * Date: 12/01/14
 * Time: 16:45
 * To change this template use File | Settings | File Templates.
 */
public class WebDriverFactory {

    private final Logger logger = Logger.getLogger(getClass().getName());
    private TestProperties testProperties = ApplicationContextHolder.getApplicationContext().getBean(TestProperties.class);
    private long remoteBrowserCreateTime = -1;
    private static int seleniumGridRequestSleep = Integer.parseInt(System.getProperty("selenium-grid-request-sleep", "500"));


    public int getCurrentPort(WebDriver driver) {
        try {
            URL url = getRemoteWebDriverUrl();
            if (url == null) {
                return -1;
            }
            HttpHost host = new HttpHost(url.getHost(), url.getPort());

            final CloseableHttpClient client = HttpClientBuilder.create().build();
            URL gridSessionUrl = new URL(url.getProtocol() + "://"
                    + url.getAuthority() + "/grid/api/testsession?session="
                    + ((RemoteWebDriver) driver).getSessionId());
            BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(
                    "POST", gridSessionUrl.toExternalForm());
            HttpResponse response = client.execute(host, request);
            JSONObject object = extractObject(response);
            URL myURL = new URL(object.getString("proxyId"));
            if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
                return myURL.getPort();
            }

        } catch (Throwable ex) {
            logger.log(Level.WARNING, "***********     Selenium Node Host is not recognized     ************ ", ex);
        }

        return -1;
    }

    public long getCurrentRemoteBrowserCreateTime() {
        return this.remoteBrowserCreateTime;
    }

    public String getCurrentRCIP(WebDriver driver) {
        try {
            URL url = getRemoteWebDriverUrl();
            if (url == null) {
                return "127.0.0.1";
            }
            HttpHost host = new HttpHost(url.getHost(), url.getPort());

            final CloseableHttpClient client = HttpClientBuilder.create().build();
            URL gridSessionUrl = new URL(url.getProtocol() + "://"
                    + url.getAuthority() + "/grid/api/testsession?session="
                    + ((RemoteWebDriver) driver).getSessionId());
            BasicHttpEntityEnclosingRequest request = new BasicHttpEntityEnclosingRequest(
                    "POST", gridSessionUrl.toExternalForm());
            HttpResponse response = client.execute(host, request);
            JSONObject object = extractObject(response);
            URL myURL = new URL(object.getString("proxyId"));
            if ((myURL.getHost() != null) && (myURL.getPort() != -1)) {
                return myURL.getHost();
            }

        } catch (Throwable ex) {
            ex.printStackTrace();
            logger.log(Level.WARNING, "***********     Selenium Node Host is not recognized     ************ ", ex);
        }

        return "Unknown Host IP";
    }

    public URL getRemoteWebDriverUrl() throws MalformedURLException {
        String url = testProperties.getRemoteDriverURL();
        if (url != null) {
            return new URL(url);
        }
        return null;
    }


    public WebDriver create(String browserType) {
        URL url;
        try {
            url = getRemoteWebDriverUrl();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new MaasUIAutomationException("Failed to get selenium grid url", e);
        }

        WebDriver webdriver;
        logger.info("DEBUG: browserType " + browserType);
        if (url != null) {
            long currentTimeMillis = System.currentTimeMillis();
            webdriver = createRemote(browserType, url);
            remoteBrowserCreateTime = System.currentTimeMillis() - currentTimeMillis;
            logger.info("-- Create Remote Driver took: " + remoteBrowserCreateTime / 1000 + " Seconds");

        } else {
            webdriver = createLocal(browserType);
        }
        TestDetailsHolder testDetailsHolder = ApplicationContextHolder.getApplicationContext().getBean(TestDetailsHolder.class);
        logger.info("DEBUG: current selenium node is " + getCurrentRCIP(webdriver) + " Running test " + testDetailsHolder.getTestDetails().getTestName());

        try {
            if (webdriver != null && !(webdriver instanceof ChromeDriver)) {
                webdriver.manage().window().maximize();
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "Webdriver failed to maximize", e);
        }
        return webdriver;
    }

    public WebDriver createRemote(String browserType, URL url) {
        return createRemoteWebDriver(browserType, url);
    }


    public WebDriver createLocal(String browserType) {
        return createLocalWebDriver(browserType);
    }

    private ChromeOptions getChromeOption() {
        ChromeOptions chromeOptions = new ChromeOptions();

        String optionStr = testProperties.getChromeOptions();
        logger.info("Chrome Option used: " + optionStr);
        String[] options = optionStr.split(",");
        chromeOptions.addArguments(Arrays.asList(options));
        String chromePac = testProperties.getChromePacFilePath();

        if (chromePac != null) {
            logger.info("Chrome using proxy pac file: " + chromePac);
            chromeOptions.addArguments("--proxy-pac-url=" + chromePac);
        }
        return chromeOptions;
    }

    /**
     * Returns an instance of {@link org.openqa.selenium.WebDriver}.
     * <p/>
     * Defers creation to a different thread to try and overcome random exceptions of type
     * <code>
     * org.openqa.selenium.WebDriverException: Connection refused
     * Command duration or timeout: 4188.18 seconds
     * ...
     * at com.maas.platform.ui.test.selenium.wd.WebDriverFactory$ScreenshootingRemoteWebDriver.<init>(WebDriverFactory.java:261)
     * at com.maas.platform.ui.test.selenium.wd.WebDriverFactory.createRemoteWebDriver(WebDriverFactory.java:176)
     * ...
     * Caused by: java.net.ConnectException: Connection refused
     * ...
     * at org.openqa.selenium.remote.RemoteWebDriver.startSession(RemoteWebDriver.java:240)
     * at org.openqa.selenium.remote.RemoteWebDriver.<init>(RemoteWebDriver.java:126)
     * </code>
     *
     * @param browserType
     * @param url
     * @return
     */
    private WebDriver createRemoteWebDriver(final String browserType, final URL url) {
        logger.info("using remote web Driver on: " + url.toString());

        int tries = 1;
        WebDriver instance = null;
        do {
            try {
                instance = createRemoteWebDriverInternal(browserType, url);
            } catch (InterruptedException e) {
                logger.info("Caught InterruptedException " + e.getMessage() + " try #" + tries);
                e.printStackTrace();
            } catch (ExecutionException e) {
                logger.info("Caught ExecutionException " + e.getMessage() + " try #" + tries);
                e.printStackTrace();
            } catch (TimeoutException e) {
                logger.info("Caught TimeoutException " + e.getMessage() + " try #" + tries);
                e.printStackTrace();
            } finally {
                tries++;
            }
        } while (instance == null && tries < 4);

        if (instance == null) {
            // Failed to create a new WebDriver instance
            throw new RuntimeException("Failed to create a new WebDriver instance, see Jenkins job console log for details");
        }
        instance.manage().window().maximize();
        instance.manage().window().setPosition(new Point(0, 0));
        int windowHeightArgument = Integer.parseInt(testProperties.getBrowserWindowSize().split(",")[0]);
        int windowWidthArgument = Integer.parseInt(testProperties.getBrowserWindowSize().split(",")[1]);
        instance.manage().window().setSize(new Dimension(windowHeightArgument , windowWidthArgument));
        return instance;
    }

    private WebDriver createRemoteWebDriverInternal(final String browserType, final URL url) throws InterruptedException, ExecutionException, TimeoutException {
        // Get desired capabilities in original thread to get the test details which are stored on the thread-based TestDetailsHolder class
        final DesiredCapabilities desiredCapabilities = getDesiredCapabilities(browserType);
        if (testProperties.isDockerSeleniumGrid()) {
            try {
                Double numPendingConnections = getSeleniumGridPendingRequestsNum();
                logger.info("Found " + numPendingConnections + " pending connections");
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
            }

            ExecutorService executorService = Executors.newFixedThreadPool(1);
            Future<WebDriver> webDriverFuture = executorService.submit(new Callable<WebDriver>() {
                @Override
                public WebDriver call() throws Exception {
                    // Get number of pending requests on grid
                    Double numPendingConnections = getSeleniumGridPendingRequestsNum();
                    while (numPendingConnections > 0) {
                        // Grid is busy. Wait in Java code instead of registering a pending request on the grid
                        // Pending requests are cancelled by Java code after 10 minutes but may remain stuck
                        // on the grid and consume resources though no one is waiting for their response
                        try {
                            Thread.sleep(seleniumGridRequestSleep);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        numPendingConnections = getSeleniumGridPendingRequestsNum();
                    }
                    return new ScreenshootingRemoteWebDriver(new HttpCommandExecutor(url), desiredCapabilities);
                }
            });

            return webDriverFuture.get(10, TimeUnit.MINUTES);
        } else {
            return new ScreenshootingRemoteWebDriver(new HttpCommandExecutor(url), desiredCapabilities);
        }
    }

    /**
     * Default DesiredCapabilities is chrome driver
     *
     * @return
     */
    protected DesiredCapabilities getDefaultDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, this.getChromeOption());
        desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        desiredCapabilities.setVersion(testProperties.getBrowserVersion());
        desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
        return desiredCapabilities;
    }

    /**
     * Default Desired Capabilities: Any-Platform,
     * Any Firefox Version, unless something is specified via a system-property "browser.version"
     * and 'Takes Screen-Shot'
     *
     * @return a DesiredCapabilities matching the above.
     */
    protected DesiredCapabilities getPerformanceDesiredCapabilities() {
        // Ask Chrome to collect "performance" logs
        DesiredCapabilities desiredCapabilities = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.INFO);
        logPrefs.enable(LogType.BROWSER, Level.ALL);
        logPrefs.enable(LogType.DRIVER, Level.ALL);
        desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
        desiredCapabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
        desiredCapabilities.setVersion(testProperties.getBrowserVersion());
        return desiredCapabilities;
    }

    private DesiredCapabilities getDesiredCapabilities(String browserType) {
        DesiredCapabilities desiredCapabilities;
        if (browserType == null || browserType.trim().equals("")) {
            return getDefaultDesiredCapabilities();
        } else {
            try {
                // Creates DesiredCapabilities thru reflection, e.g. calls DesiredCapabilities.chrome() for creating chrome capabilities
                Method method = DesiredCapabilities.class.getDeclaredMethod(browserType);
                int modifiers = method.getModifiers();
                if (Modifier.isStatic(modifiers)) {
                    desiredCapabilities = (DesiredCapabilities) method.invoke(null);
                    LoggingPreferences logPrefs = new LoggingPreferences();
                    logPrefs.enable(LogType.BROWSER, Level.ALL);
                    logPrefs.enable(LogType.DRIVER, Level.ALL);
                    desiredCapabilities.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
                    TestDetails testDetails = ApplicationContextHolder.getApplicationContext().getBean(TestDetailsHolder.class).getTestDetails();
                    String testName = testDetails.getTestName();
                    desiredCapabilities.setCapability("Test name", testName);

                } else {
                    throw new RuntimeException("******* Error getting capabilities of browser '" + browserType + "'");
                }
            } catch (Exception e) {
                // error getting capabilities of the specified browser - do not fail.
                throw new RuntimeException("******* Error getting capabilities of browser '" + browserType + "':", e);
            }
        }

        if (browserType.equalsIgnoreCase("chrome") && testProperties.isDockerSeleniumGrid()) {
            ChromeOptions options = getChromeOption();
            // Using --start-maximized flag does not work well on Vagrant Ubuntu
            String windowSizeArgument = "--window-size=" + testProperties.getBrowserWindowSize();
            options.addArguments(windowSizeArgument);
            desiredCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

            String proxyServer = testProperties.getBrowserProxyServer();
            Proxy proxy = new Proxy();
            proxy.setProxyType(Proxy.ProxyType.MANUAL);
            proxy.setFtpProxy(proxyServer);
            proxy.setHttpProxy(proxyServer);
            proxy.setSslProxy(proxyServer);
            desiredCapabilities.setCapability("proxy", proxy);
        } else if (browserType.equalsIgnoreCase("firefox") && testProperties.isDockerSeleniumGrid()) {
            String proxyServer = testProperties.getBrowserProxyServer();
            Proxy proxy = new Proxy();
            proxy.setProxyType(Proxy.ProxyType.MANUAL);
            proxy.setFtpProxy(proxyServer);
            proxy.setHttpProxy(proxyServer);
            proxy.setSslProxy(proxyServer);
            desiredCapabilities.setCapability("proxy", proxy);

            FirefoxProfile fp = new FirefoxProfile();

//            fp.setEnableNativeEvents(true);
            fp.setPreference("browser.privatebrowsing.autostart", true);
            fp.setPreference("intl.accept_languages", "en-US");

            desiredCapabilities.setCapability(FirefoxDriver.PROFILE, fp);
        }

        if (testProperties.isPerformanceTest()) {
            desiredCapabilities.merge(getPerformanceDesiredCapabilities());
        }
        if (testProperties.isWindowsBasedHost()) {
            desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.WINDOWS);
        }
        else{
            desiredCapabilities.setCapability(CapabilityType.PLATFORM, Platform.LINUX);
        }
        return desiredCapabilities;
    }

    static class ScreenshootingRemoteWebDriver extends RemoteWebDriver implements TakesScreenshot {

        public ScreenshootingRemoteWebDriver(CommandExecutor commandExecutor, DesiredCapabilities desiredCapabilities) {
            super(commandExecutor, desiredCapabilities);
            setFileDetector(new LocalFileDetector());
        }

        @Override
        public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
            // Get the screenshot as base64. (copied from FirefoxDriver)
            String base64 = execute(DriverCommand.SCREENSHOT).getValue().toString();
            // ... and convert it.
            return target.convertFromBase64Png(base64);
        }
    }

    private WebDriver createLocalWebDriver(String browserType) {
        WebDriver driver;
        if ("chrome".equals(browserType)) {
            String path = testProperties.getChromeDriverFullPath();
            if (!StringUtils.isBlank(path) && !path.endsWith(File.separator)) {
                path += File.separator;
            }
            String chromeExecutable = testProperties.chromeDriverExecutableName();
            String chromeDriverPath = path + chromeExecutable;
            logger.info("Using local chromeDriverPath=" + chromeDriverPath);
            File chromeDriverFile = new File(chromeDriverPath);

            File chromeDriverLog = null;
            try {
                chromeDriverLog = createTargetFile("chromedriver.log");
                if (!chromeDriverLog.exists()) {
                    try {
                        chromeDriverLog.getParentFile().mkdirs();
                        chromeDriverLog.createNewFile();
                    } catch (IOException e) {
                        // Swallow exception and continue
                        e.printStackTrace();
                    }
                }
                if (chromeDriverLog != null && chromeDriverLog.exists()) {
                    logger.info("DEBUG: logging chromedriver to " + chromeDriverLog.getAbsolutePath());
                } else {
                    logger.info("DEBUG: chromedriver log does not exists");
                }
            } catch (URISyntaxException e) {
                logger.severe("DEBUG: chromedriver.log was not created");
                e.printStackTrace();
            }
            ChromeDriverService.Builder builder = new ChromeDriverService.Builder();
            ChromeDriverService service =
                    builder
                            .usingDriverExecutable(chromeDriverFile)
                            .usingAnyFreePort()
                            .withLogFile(chromeDriverLog)
                            .build();


///*          // Attempt complete #2
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("download.default_directory", System.getProperty("browserFileDownloadFolderPath"));
            prefs.put("safebrowsing.enabled", true);
            DesiredCapabilities caps = DesiredCapabilities.chrome();

//            ChromeOptions options = new ChromeOptions(); //no options
            ChromeOptions options = getChromeOption(); // defined in open Browser BB

            options.setExperimentalOption("prefs", prefs);
            caps.setCapability(ChromeOptions.CAPABILITY, options);

//            driver = new ChromeDriver(service, caps);
            driver = new ChromeDriver(options);
//            */


            /* // Maas way
            ChromeOptions options = getChromeOption();
            if (testProperties.isPerformanceTest()) {
                DesiredCapabilities capabilities = getPerformanceDesiredCapabilities();
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(service, capabilities);
            } else {
                driver = new ChromeDriver(service, options);
            }
//             */
        } else if ("firefox".equals(browserType)) {
            FirefoxProfile fp = new FirefoxProfile();
//            fp.setEnableNativeEvents(true);
            fp.setPreference("browser.privatebrowsing.autostart", true);
            fp.setPreference("intl.accept_languages", "en-US");

            //ooJsystem added options
            fp.setPreference("browser.download.panel.shown", false);
            fp.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream");
            fp.setPreference("browser.helperApps.alwaysAsk.force", false);
            fp.setPreference("browser.download.folderList", 2);
            fp.setPreference("browser.download.dir", System.getProperty("browserFileDownloadFolderPath"));

            DesiredCapabilities dc = DesiredCapabilities.firefox();
            dc.setCapability(FirefoxDriver.PROFILE, fp);
            driver = new FirefoxDriver(dc);
        } else if ("internetexplorer".equals(browserType)) {
            driver = new InternetExplorerDriver();
        } else if ("safari".equals(browserType)) {
            driver = new SafariDriver();
        } else if ("edge".equals(browserType)) {
            DesiredCapabilities capabilities = DesiredCapabilities.edge();
            driver = new EdgeDriver(capabilities);
        } else {
            // default
            FirefoxProfile profile = new FirefoxProfile();
            profile.setPreference("browser.privatebrowsing.autostart", true);
            driver = null;
        }
        return driver;
    }

    private static File createTargetFile(String path) throws URISyntaxException {
        final URL targetUrl = codeLocationFromPath("target");
        File targetFolder = new File(targetUrl.toURI());
        return new File(targetFolder.getAbsolutePath() + File.separator + "archive" + File.separator + path);
    }

    /**
     * Creates a code location URL from a file path
     *
     * @param filePath the file path
     * @return A URL created from File
     * @throws RuntimeException if URL creation fails
     */
    private static URL codeLocationFromPath(String filePath) {
        try {
            return new File(filePath).toURI().toURL();
        } catch (Exception e) {
            throw new RuntimeException("Invalid file path " + filePath);
        }
    }


    /**
     * Extract JSONObject from HttpResponse
     *
     * @param response - The HttpResponse
     * @return JSONObject - The JSONObject
     */
    private JSONObject extractObject(HttpResponse response) {
        BufferedReader reader = null;

        try {
            InputStream content = response.getEntity().getContent();
            reader = new BufferedReader(new InputStreamReader(content, "UTF-8"));
            String json = reader.readLine();
            return (json != null) ? new JSONObject(json) : null;
        } catch (Exception e) {
            logger.warning("Failed to extract JsonObject from the response");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (Exception e2) {
                }
            }
        }
        return null;
    }

    /**
     * Class for sending a GET request with body, see http://stackoverflow.com/questions/12535016/apache-httpclient-get-with-body
     */
    private static class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {
        public final static String METHOD_NAME = "GET";

        @Override
        public String getMethod() {
            return METHOD_NAME;
        }
    }

    /**
     * Get number of pending requests on Selenium grid
     *
     * @return
     * @throws IOException
     * @throws URISyntaxException
     */
    private Double getSeleniumGridPendingRequestsNum() throws IOException,
            URISyntaxException {
        // Get url for pending requests from grid hub e.g. -- http://mydtbld0028g.isr.hp.com:4444/wd/hub
        String url = testProperties.getRemoteDriverURL();
        if (url == null) {
            throw new MaasUIAutomationException("Remote web driver url can't be empty");
        }
        String pendingRequestsUrl = url.replace("wd/hub", "grid/api/hub");
        HttpGetWithEntity getPendingRequests = new HttpGetWithEntity();
        URL pendingRequests = new URL(pendingRequestsUrl);
        getPendingRequests.setURI(pendingRequests.toURI());
        String PENDING_REQUEST_COUNT = "newSessionRequestCount";
        getPendingRequests.setEntity(new StringEntity("{\"configuration\":[\""
                + PENDING_REQUEST_COUNT + "\"]}",
                ContentType.APPLICATION_JSON));
        HttpClient client = HttpClientBuilder.create().build();
        HttpResponse response = client.execute(getPendingRequests);
        BufferedReader rd = new BufferedReader(new InputStreamReader(
                response.getEntity().getContent()));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        Map<Object, Object> responseKeys =
                new GsonBuilder().create().fromJson(result.toString(), Map.class);
        Object newSessionRequestCount = responseKeys.get(PENDING_REQUEST_COUNT);
        // Example return value is "1.0"
        return Double.valueOf(newSessionRequestCount.toString());
    }
}
