package com.ui.automation.app.listeners;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.reporter.api.JunitLifeCycleReporter;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Spring JUnit test listener for implementing beforeClass and afterClass methods
 * with access to beans (JUnit's @BeforeClass and @AfterClass are static methods
 * which cannot see injected beans)
 */
//we are using this listener only because the ReportRunListener need to report the after class after all is done
public class ReportFlushListener extends AbstractTestExecutionListener {

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        JunitLifeCycleReporter reporter = ApplicationContextHolder.getApplicationContext().getBean(JunitLifeCycleReporter.class);
        System.out.println("REPORTER: Tests class '" + testContext.getTestClass().getName() + "' Finished!!!");
        reporter.testClassFinish(testContext);
    }


}
