package com.ui.automation.app.listeners;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.reporter.api.AutoStepReporter;
import com.ui.automation.selenium.browser.BrowserPage;
import com.ui.automation.selenium.lifecycle.PageTestContext;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Spring JUnit test listener for implementing beforeClass and afterClass methods
 * with access to beans (JUnit's @BeforeClass and @AfterClass are static methods
 * which cannot see injected beans)
 */
public class BrowserManagerListener extends AbstractTestExecutionListener {

    private BrowserPage browserPage;
    private PageTestContext pageTestContext;


    private Reporter reporter() {
        final Reporter reporter = ApplicationContextHolder.getApplicationContext().getBean(Reporter.class);
        return reporter;
    }

    private AutoStepReporter stepReporter() {
        final AutoStepReporter stepReporter = ApplicationContextHolder.getApplicationContext().getBean(AutoStepReporter.class);
        return stepReporter;
    }

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        super.beforeTestClass(testContext);
        reporter().info("Performing Tenant Listener - Before TestPages Class");
        TestProperties testProperties = testContext.getApplicationContext().getBean(TestProperties.class);
        String browserType = testProperties.getBrowserType();
        try {
            // Initialize ApplicationContextHolder class

            reporter().info("Initialize Page TestPages Context");
            pageTestContext = testContext.getApplicationContext().getBean(PageTestContext.class);
            pageTestContext.setup();
            reporter().info("Opening browser");
            browserPage = testContext.getApplicationContext().getBean(BrowserPage.class);
            browserPage.open(browserType);

        } catch (Exception e) {
            e.printStackTrace();
            stepReporter().stepGeneralFailure("Failed to setup test ", e, true);
            throw e;
        }
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        try {
            browserPage.quit();
            pageTestContext.teardown();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}
