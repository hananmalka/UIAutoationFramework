package com.ui.automation.app.listeners;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.reporter.api.JunitLifeCycleReporter;
import com.ui.automation.app.listeners.helpers.RoleHolder;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Spring JUnit test listener for implementing beforeClass and afterClass methods
 * with access to beans (JUnit's @BeforeClass and @AfterClass are static methods
 * which cannot see injected beans)
 */
//we are using this listener only because the ReportRunListener will not report testStarts
public class ReportCreationListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        System.out.println("REPORTER: Tests Class: '" + testContext.getTestClass() + "' Start!!!");
        testContext.getApplicationContext().getBean(ApplicationContextHolder.class);
        TestProperties testProperties = testContext.getApplicationContext().getBean(TestProperties.class);
        String browserType = testProperties.getBrowserType();
        JunitLifeCycleReporter reporter = ApplicationContextHolder.getApplicationContext().getBean(JunitLifeCycleReporter.class);
        final String testNameAppendix = " [" + RoleHolder.get() + "]";
        reporter.testClassStart(testContext.getTestClass(), browserType, testNameAppendix);
    }



}
