package com.ui.automation.selenium.wd;

import com.applitools.eyes.Eyes;
import com.applitools.eyes.MatchLevel;
import com.applitools.eyes.ProxySettings;
import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.common.element.items.SpecialKeys;
import com.ui.automation.common.exception.MaasUIAutomationException;
import com.ui.automation.common.junit.TestDetailsHolder;
import com.ui.automation.selenium.service.LocatorService;
import com.ui.automation.selenium.wd.angular.ClientSideScripts;
import org.imgscalr.Scalr;
import org.openqa.selenium.*;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Driver implementation.
 * <p/>
 * <strong>MUST</strong> be retrieved only thru DriverTestContext since it handles multi-threaded execution
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MaasDriverImpl implements MaasDriver {

    private static final float QUALITY_RATIO = 0.8f;
    private WebDriver driver;
    private Eyes eyes;
    private DriverServices driverServices;
    private Actions actions;
    private Expects expects;
    private SpecificElementExecuter specificElementExecuter;
    private Finds finds;
    private Logger logger = Logger.getLogger(getClass().getName());

    @Autowired
    private TestProperties testProperties;

    @Autowired
    private ClientSideScripts clientSideScripts;

    @Autowired
    private LocatorService locatorService;
    private String currentRCIP;
    private int RCPort = -1;
    private long currentRemoteCreationTimeMS = -1;

    @PostConstruct
    void postConstruct() {
        driverServices = new DriverServices(testProperties.getDriverTimeout());
        actions = new ActionsImpl(driverServices);
        expects = new ExpectsImpl(driverServices);
        specificElementExecuter = new SpecificElementExecuterImpl(driverServices);
        finds = new FindsImpl(driverServices);
    }

    @Override
    public Actions actions() {
        return actions;
    }

    @Override
    public Expects expects() {
        return expects;
    }

    @Override
    public Finds finds() {
        return finds;
    }

    @Override
    public Browser browser() {
        return new BrowserImpl();
    }

    @Override
    public SpecificElementExecuter specificElementExecuter() {
        return specificElementExecuter;
    }

    /**
     * This method is public but not in the interface by design!!
     * <p/>
     * It is not meant to be used by modules that have a dependency on maas-selenium-api, it
     * is meant to be used only internally within this module, maas-selenium-impl.
     * <p/>
     * DO NOT ADD THIS METHOD TO THE Browser INTERFACE
     *
     * @return
     */
    public DriverServices getDriverServices() {
        return driverServices;
    }

    @Override
    public String getRemoteSeleniumNodeHost() {
        return currentRCIP;
    }

    @Override
    public long getCurrentRemoteCreationTimeMS() {
        return currentRemoteCreationTimeMS;
    }

    @Override
    public int getRemoteSeleniumNodePort() {
        return RCPort;
    }

    private class BrowserImpl implements Browser {

        @Override
        public void open(String browserType) {
            if (driver != null) {
                throw new MaasUIAutomationException("Browser is already open");
            }
            WebDriverFactory webDriverFactory = new WebDriverFactory();
            driver = webDriverFactory.create(browserType);
            currentRCIP = webDriverFactory.getCurrentRCIP(driver);
            RCPort = webDriverFactory.getCurrentPort(driver);

            if (!testProperties.isVisualTest()) {
                driverServices.setVisual(NoVisuals.getInstance());
            }
            else {
                eyes = new Eyes();
                // This is your api key, make sure you use it in all your tests.
                eyes.setApiKey("GWZCo4kIQpzJ109eMTGjJfEKwxoeRHbntYsn9Lug1lRsU110");
                eyes.setProxy(new ProxySettings(testProperties.getBrowserProxyServer()));
                eyes.setMatchLevel(MatchLevel.LAYOUT2);
                eyes.setMatchTimeout(5);
                eyes.setSaveNewTests(true);

                // Make sure to use the returned driver from this point on.
                driver = eyes.open(driver, "Service Anywhere", testDetailsHolder().getTestDetails().getTestName());
                driverServices.setVisual(new VisualImpl(eyes, driver));
            }

            driverServices.setDriver(driver);
            currentRemoteCreationTimeMS = webDriverFactory.getCurrentRemoteBrowserCreateTime();
            logger.info("Open driver instance for browser " + browserType + ". context " + driver);
        }

        @Override
        public void quit() {
            if (driver == null) {
                throw new MaasUIAutomationException("Browser is already closed");
            }
            if (testProperties.isPerformanceTest()) {
                this.profiling().getResults();
            }
            logger.info("Quit driver instance. context " + driver);
            try {
                closeEyes();
            }
            catch (Exception e) {

                throw e;
            }
            finally {
                driver.quit();
                nullifyDriver();
            }
        }

        private void closeEyes() {
            if (eyes != null) {
                try {
                    eyes.close();
                }
                catch (Exception e) {
                    reporter().error(e.getMessage());
                }
                finally {
                    // Abort test in case of an unexpected error.
                    eyes.abortIfNotClosed();
                }
                eyes = null;
            }
            driverServices.setVisual(null);
        }

        private void nullifyDriver() {
            driver = null;
            driverServices.setDriver(driver);
        }

        @Override
        public void close() {
            if (driver == null) {
                throw new MaasUIAutomationException("Browser is already closed");
            }
//            if (testProperties.isPerformanceTest()) {
//                this.profiling().getResults();
//            }
            try {
//                closeEyes();
            }
            finally {
                driver.close();
//                nullifyDriver();
            }
        }

        @Override
        public void get(String url) {
            logger.info("Navigating to " + url);
            clientSideScripts.deferBootstrap();
            try {
                waitForAngular();
            } catch (MaasUIAutomationException e) {
                // Log error and continue - by calling driver.get the application is reloaded anyhow
                logger.warning("Failed to wait for Angular " + e.getMessage());
            }
            driver.get(url);
            clientSideScripts.deferBootstrap();
        }

        private void waitForAngular() throws MaasUIAutomationException {
            if (driverServices.isAngularDefined()) {
                // Wait for angular to syncronize and complete outstanding $http and $timeout requests
                // This is to prevent tests methods in the same class to start in an inconsistent state
                // e.g. a test that starts after the previous one clicked "save" and the saving mask is still up while
                // the current test tries to navigate away
                try {
                    ((ActionsImpl) actions).waitForAngular();
                } catch (Exception e) {
                    throw new MaasUIAutomationException("Failed to wait for Angular", e);
                }
            }
        }

        // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//        @Override
//        public void navigateToAppUrl(String appUrl) {
//            if (!appUrl.startsWith("/")) {
//                appUrl = "/" + appUrl;
//            }
//            String tenantIdParam = ((appUrl.indexOf('?') < 0) ? '?' : '&') + testProperties.getTenantIdQueryParameter();
//            get(testProperties.getAppBaseUrl() + appUrl + tenantIdParam + '&' + testProperties.getQueryParameters());
//        }

        @Override
        public void savePageSource(Path targetDir, String fileName) {
            final String elementSource = driver.getPageSource();
            Path targetFile = Paths.get(targetDir.toString(), fileName);
            try {
                Files.createDirectories(targetDir);
                Files.createFile(targetFile);
                logger.info("Saving HTML Source to " + targetFile.toString());
            } catch (FileAlreadyExistsException e) {
                // Ignore, just save the file
            } catch (IOException e) {
                throw new MaasUIAutomationException("Failed to create directories" + targetFile, e);
            }
            try (FileWriter fileWriter = new FileWriter(targetFile.toFile())) {
                fileWriter.write(elementSource);
            } catch (IOException e) {
                throw new MaasUIAutomationException("Failed to save element source " + fileName, e);
            }
        }

        @Override
        public String getConsoleLog() {
            Logs logs = driver.manage().logs();

            String result = "";
            if (logs.getAvailableLogTypes().contains(LogType.BROWSER)) {
                List<LogEntry> entries = logs.get(LogType.BROWSER).getAll();
                if (entries.size() > 0) {
                    for (LogEntry entry : entries) {
                        result += formatLogEntry(entry) + "\n";
                    }
                }
            }
            return result;
        }


        @Override
        public void saveConsoleLog(Path targetDir, String fileName) {
            Logs logs = driver.manage().logs();
            logger.info("Log types: " + logs.getAvailableLogTypes());
            // Print browser console
            if (logs.getAvailableLogTypes().contains(LogType.BROWSER)) {
                saveConsoleLog(logs.get(LogType.BROWSER).getAll(), targetDir, fileName);
            }
        }

        private void saveConsoleLog(List<LogEntry> entries, Path targetDir, String fileName) {
            Path targetFile = Paths.get(targetDir.toString(), fileName);
            try {
                Files.createDirectories(targetDir);
                Files.createFile(targetFile);
                logger.info("Saving Browser Log to " + targetFile.toString());
            } catch (FileAlreadyExistsException e) {
                // Ignore, just save the file
            } catch (IOException e) {
                throw new MaasUIAutomationException("Failed to create directories" + targetFile, e);
            }
            printLogToFile(targetFile, entries);
        }

        private void printLogToFile(Path targetFile, List<LogEntry> entries) {
            if (entries.size() > 0) {
                logger.info(entries.size() + " " + LogType.BROWSER + " log entries found, writing to file " + targetFile);
                try (PrintWriter writer = new PrintWriter(new FileWriter(targetFile.toFile()))) {
                    for (LogEntry entry : entries) {
                        writer.println(formatLogEntry(entry));
                    }
                } catch (IOException e) {
                    throw new MaasUIAutomationException("Failed to print log to file " + targetFile, e);
                }
            }
        }

        private String formatLogEntry(LogEntry entry) {
            return new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage();
        }

        @Override
        public void maximize() {
            driver.manage().window().maximize();
        }

        @Override
        public void saveScreenshot(Path targetDir, String fileName) {
            try {
                // Save screenshot in temp dir
                final File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                //reduce saved image file size by reducing it's quality
                FileInputStream inputStream = new FileInputStream(screenshot);
                BufferedImage originalImage = ImageIO.read(inputStream);

                Files.createDirectories(targetDir);
                Path targetFile = Paths.get(targetDir.toString(), fileName);
                logger.info("Saving screenshot to " + targetFile.toString());

                File file = new File(targetFile.toString());
                BufferedImage resizedImage = Scalr.resize(originalImage, Scalr.Method.QUALITY, Scalr.Mode.FIT_EXACT, originalImage.getWidth(), originalImage.getHeight(), Scalr.OP_ANTIALIAS);
                ImageIO.write(resizedImage, "png", file);
            } catch (FileAlreadyExistsException e) {
                // Ignore, just save the file
            } catch (IOException ioe) {
                logger.severe("Failed to capture screenshot");
                ioe.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
        }

        private TestDetailsHolder testDetailsHolder() {
            return ApplicationContextHolder.getApplicationContext().getBean(TestDetailsHolder.class);
        }

        private Reporter reporter() {
            return ApplicationContextHolder.getApplicationContext().getBean(Reporter.class);
        }

        @Override
        public void pressKey(CharSequence key) {
            reporter().debug("Pressing keyboard key [" + ((SpecialKeys) key).name() + "]");
            driver.switchTo().activeElement().sendKeys(key);
            driver.switchTo().defaultContent();
        }

        @Override
        public String getBrowserName() {
            String browserName = "";
            String scriptResult = (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
            if (scriptResult != null && !(scriptResult = scriptResult.trim()).isEmpty()) {
                if (scriptResult.contains("Chrome")) {
                    browserName = "Chrome";
                } else if (scriptResult.contains("Firefox")) {
                    browserName = "Firefox";
                } else if (scriptResult.contains("MSIE")) {
                    browserName = "InternetExplorer";
                }
            }
            return browserName;
        }

        @Override
        public String getBaseHref() {
            String baseHref = "";
            try {
                WebElement baseElement = driverServices.getFluentWait().until(new ExpectedCondition<WebElement>() {
                    @Override
                    public WebElement apply(WebDriver webDriver) {
                        final List<WebElement> baseElements = driver.findElements(By.tagName("base"));
                        // From https://developer.mozilla.org/en-US/docs/Web/HTML/Element/base
                        // If multiple <base> elements are specified, only the first href and first target value are used; all others are ignored.
                        if (baseElements.size() > 0) {
                            return baseElements.get(0);
                        }
                        // Keep waiting for base tag to be present in HTML
                        return null;
                    }
                });
                baseHref = baseElement.getAttribute("href");
            } catch (Exception e) {
                logger.warning("Failed to get base href. " + e.getMessage());
            }
            return baseHref;
        }

        /**
         * Browser back action
         */
        @Override
        public void back() {
            // Wait for angular to sync, e.g. complete async requests
            try {
                waitForAngular();
            } catch (MaasUIAutomationException e) {
                // Log error and continue
                logger.warning("Failed to wait for Angular " + e.getMessage());
                e.printStackTrace();
            }
            driver.navigate().back();
        }

        @Override
        public void refresh() {
            logger.info("Refreshing element");
            clientSideScripts.deferBootstrap();
            driver.navigate().refresh();
            clientSideScripts.deferBootstrap();
            // Wait for refresh to complete
            try {
                waitForAngular();
            } catch (MaasUIAutomationException e) {
                // Log error and continue - WebDriver refresh was already executed
                logger.warning("Failed to wait for Angular " + e.getMessage());
                e.printStackTrace();
            }
        }

        /**
         * @return the current element URL
         */
        @Override
        public String getCurrentURL() {
            return driver.getCurrentUrl();
        }

        @Override
        public com.ui.automation.selenium.wd.Alert alert() {
            return new AlertImpl(driver, testProperties.getDriverTimeout());
        }

        @Override
        public Profiling profiling() {
            return new ProfilingImpl(driverServices);
        }

        @Override
        public boolean isAtTopOfScroll() {
            return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop === 0", driver.findElement(By.xpath("//body")));
        }


        @Override
        public int getTimeZoneOffset() {
            Long offset = (Long) ((JavascriptExecutor) driver).executeScript("return new Date().getTimezoneOffset();");
            if (offset != null) {
                return -offset.intValue();
            }
            throw new MaasUIAutomationException("Failed to retrieve browser timezone offset");
        }

        @Override
        public boolean isDateInDst(int year, int month, int day, int hour) {
            return (boolean) ((JavascriptExecutor) driver).executeScript("return moment({year:" + year + ", month:" + month + ", day:" + day + ", hour:" + hour + "}).isDST()");

        }

        @Override
        public void switchToFrame(String frameId) {
            driver = driverServices.getFluentWait().until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id(frameId)));
        }

        @Override
        public void switchToTopFrame() {
            driver = driver.switchTo().defaultContent();
        }

        @Override
        public void closeCurrentTab() {
            // find a tab to switch to after closing current tab
            final Set<String> windowHandles = driver.getWindowHandles();
            final String windowHandle = driver.getWindowHandle();
            String otherTab = null;
            for (String tab : windowHandles) {
                if (!tab.equals(windowHandle)) {
                    otherTab = tab;
                    break;
                }
            }

            if (otherTab == null) {
                // only 1 tab open
                driver.quit();
            } else {
                driver.close();
                driver = driver.switchTo().window(otherTab);
            }
        }

        @Override
        public void switchToTab() {
            final Set<String> windowHandles = driver.getWindowHandles();
            if (windowHandles.size() > 2) {
                throw new MaasUIAutomationException("Too many open tabs [" + windowHandles.size() + "] method assumes only 2 tabs are open");
            } else if (windowHandles.size() == 2) {
                final String windowHandle = driver.getWindowHandle();
                for (String tab : windowHandles) {
                    if (!tab.equals(windowHandle)) {
                        driver = driver.switchTo().window(tab);
                        break;
                    }
                }
            } else {
                logger.warning("There is only one tab...");
            }
        }

        @Override
        public void openNewTab() {
            ((JavascriptExecutor)driverServices.getDriver()).executeScript("window.open();");
        }

        @Override
        public void moveMouseToTopLeft() {
            org.openqa.selenium.interactions.Actions builder = new org.openqa.selenium.interactions.Actions(driverServices.getDriver());
            builder.moveToElement(driver.findElement(By.xpath("//body")), 0, 0).perform();
        }

        @Override
        public long getTime() {
            return ((long)((JavascriptExecutor)driver).executeScript("return (new Date()).getTime()"));
        }

        @Override
        public String getTitle() {
            return driver.getTitle();
        }

    }
}
