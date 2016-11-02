package com.ui.automation.app.runners.listeners;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.recorder.api.TestRecorder;
import com.ui.automation.reporter.api.JunitLifeCycleReporter;
import com.ui.automation.selenium.wd.MaasDriver;
import com.ui.automation.selenium.browser.AlertPage;
import com.ui.automation.selenium.browser.BrowserPage;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;
import org.openqa.selenium.UnhandledAlertException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/**
 * TestPages run report
 */
public class ReportRunListener extends RunListener {
    private Logger logger = Logger.getLogger(getClass().getName());
    private static String TEST_DATA_DIR = "test_data";

    private final static ReportRunListener instance = new ReportRunListener();

    private ReportRunListener() {
        // Singleton
    }

    public static synchronized ReportRunListener getInstance() {
        return instance;
    }

    private JunitLifeCycleReporter getReporterActions() {
        return ApplicationContextHolder.getApplicationContext().getBean(JunitLifeCycleReporter.class);
    }

    //this will be reported ONLY when all tests is done
    @Override
    public void testRunFinished(Result result) throws Exception {
        getReporterActions().allTestFinished(result);
    }


    private MaasDriver getMaasDriver() {
        return ApplicationContextHolder.getApplicationContext().getBean(MaasDriver.class);
    }

    private String getRemoteDriverHost() {
        return getMaasDriver().getRemoteSeleniumNodeHost();
    }

    /**
     * Called with test method start
     * @param description
     * @throws Exception
     */
    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        try {
            getRecorder().startRecording(description);
        } catch (Exception dataCollectorException) {
            logger.severe("Failed to start recording");
            dataCollectorException.printStackTrace();
        }
        getReporterActions().testMethodStart(description);
        String remoteDriverHost = getRemoteDriverHost();
        if (remoteDriverHost != null) {
            getReporter().info("Running on remote selenium node: " + remoteDriverHost);
        }


    }

    @Override
    public void testFinished(Description description) throws Exception {
        super.testFinished(description);

        try {
            getRecorder().stopAndDeleteRecording();
        } catch (Exception dataCollectorException) {
            logger.severe("Failed to stop recording");
            dataCollectorException.printStackTrace();
        }
        getReporterActions().testMethodFinished(description);
    }

    private Reporter getReporter() {
        final Reporter reporter = ApplicationContextHolder.getApplicationContext().getBean(Reporter.class);
        return reporter;
    }

    private TestRecorder getRecorder() {
        final TestRecorder testRecorder = ApplicationContextHolder.getApplicationContext().getBean(TestRecorder.class);
        return testRecorder;
    }

    private BrowserPage getBrowser() {
        final BrowserPage browser = ApplicationContextHolder.getApplicationContext().getBean(BrowserPage.class);
        return browser;
    }

    @Override
    public void testFailure(Failure failure) throws Exception {
        super.testFailure(failure);


        if(failure.getDescription().getMethodName()!=null){
            logger.info("REPORTER: TestPages failure - Class: [" + failure.getDescription().getClassName() + "] Method [" + failure.getDescription().getMethodName() + "]");
            failure.getException().printStackTrace();
        }
        else{
            logger.info("REPORTER: Tests Class failure - Class: [" + failure.getDescription().getClassName() + "]");
            failure.getException().printStackTrace();
        }

        getReporter().debug("-- Start handling test failure -- ");
        String baseFolder = getFileName(failure.getDescription());
        String relativePath = "../" + TEST_DATA_DIR + "/" + baseFolder + "/";
        Path targetFolder = getTargetDir(baseFolder);
      //  Boolean isSucceed = true;
        String imgPath ="";
        String htmlSrcPath ="";
        String recordingPath ="";
        String browserConsolePath ="";



        //LIMITATION: The next code is comment out since there is no reporter capture on failure when it occurs on before/after class.
        // So we don't need it for now.



        try {
            getReporter().debug("Saving page screenshot");
            getBrowser().saveScreenshot(targetFolder, "screenshot.png");
            imgPath = relativePath + "screenshot.png";
        } catch (UnhandledAlertException uae) {
            AlertPage alert = getBrowser().alert();
            logger.severe("[TestPages cleanup] Accepting alert with text = " + alert.getText());
            getReporter().error("[TestPages cleanup] Failed to take screenshot since there is Unhandled Alert with text: " + alert.getText());
            getReporter().info("Accepting the alert!!!");
            alert.accept();
        } catch (Exception dataCollectorException) {
            logger.severe("Failed to save screenshot");
            getReporter().error("Failed to take screenshot!!");
            dataCollectorException.printStackTrace();
        }
        try {
            getBrowser().consoleLog("### TestPages \"" + failure.getDescription().getDisplayName() + "\" ended ###");
            getReporter().debug("Saving browser log");
            getBrowser().saveConsoleLog(targetFolder, "browser-console.txt");
            browserConsolePath = relativePath +  "browser-console.txt";
        } catch (Exception dataCollectorException) {
            logger.severe("Failed to save console log");
            getReporter().error("Failed to save console log");
            dataCollectorException.printStackTrace();
        }
        try {
            getReporter().debug("Saving page source");
            getBrowser().savePageSource(targetFolder, "page-source.html");
            htmlSrcPath = relativePath +  "page-source.html";
        } catch (Exception dataCollectorException) {

            logger.severe("Failed to save page source");
            getReporter().error("Failed to save page source");
            dataCollectorException.printStackTrace();
        }

        try {
            getReporter().debug("Saving video record");
            if(getRecorder().stopAndSaveRecording(targetFolder, "recording.mov")){
                recordingPath = relativePath +  "recording.mov";
            }

        } catch (Exception dataCollectorException) {

            logger.severe("Failed to save video recording");
            getReporter().error("Failed to save video recording");
            dataCollectorException.printStackTrace();
        }

        getReporterActions().testFailure(failure,imgPath,recordingPath,htmlSrcPath,browserConsolePath);
    }

    @Override
    public void testIgnored(Description description) throws Exception {
        super.testIgnored(description);
        try {
            getReporterActions().testIgnored(description);
        } catch (RuntimeException e) {
            // Ignore the exception, failing to report an ignored test shouldn't fail the build
            logger.severe("Failed to report ignored test class = " + description.getClassName() + " method = " + description.getMethodName() +
                    e.getMessage());
        }

    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        super.testAssumptionFailure(failure);
        try {
            getReporterActions().testAssumptionFailure(failure);
            logger.info("Found AssumptionFailure TestPages: " + failure.getDescription().getClassName() + " method = " + failure.getDescription().getMethodName());

        } catch (RuntimeException e) {
            // Ignore the exception, failing to report an ignored test shouldn't fail the build
            logger.severe("Failed to report ignored test class = " + failure.getDescription().getClassName() + " method = " + failure.getDescription().getMethodName() +
                    e.getMessage());
        }
        finally {

        }
    }

    /**
     * Creates a directory under target in the form of target/test_data/<dir to create>
     *
     * @param directoryToCreate
     * @return
     */
    private Path getTargetDir(String directoryToCreate) {
        final URL targetUrl = codeLocationFromPath("target");
        Path targetPath;
        try {
            targetPath = Paths.get(targetUrl.toURI());
        } catch (URISyntaxException e) {
            logger.severe("Failed to get code location from targetPath");
            throw new RuntimeException("Failed to get code location from targetPath", e);
        }
        // Creates a directory under target in the form of target/test_data/<dir to create>
        return Paths.get(targetPath.toAbsolutePath().toString(), TEST_DATA_DIR, directoryToCreate);
    }

    /**
     * Creates a code location URL from a file path
     *
     * @param filePath the file path
     * @return A URL created from File
     */
    private URL codeLocationFromPath(String filePath) {
        try {
            return new File(filePath).toURI().toURL();
        } catch (Exception e) {
            throw new RuntimeException("InvalidCodeLocation " + filePath, e);
        }
    }


    /**
     * Returns a file name based on current time and date and test class and method
     *
     * @param description test description
     * @return
     */
    protected String getFileName(Description description) {
        return getBrowserName() + "-" + description.getTestClass().getSimpleName() + "-" + description.getMethodName() + "-" + getTimestamp();
    }

    private String getBrowserName() {
        TestProperties testProperties = ApplicationContextHolder.getApplicationContext().getBean(TestProperties.class);
        return testProperties.getBrowserType();
    }

    private String getTimestamp() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy-HH-mm-ss");
        Date date = new Date();
        return dateFormat.format(date);
    }


}
