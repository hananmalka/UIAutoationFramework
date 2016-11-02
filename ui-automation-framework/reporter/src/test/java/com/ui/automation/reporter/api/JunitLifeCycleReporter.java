package com.ui.automation.reporter.api;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.test.context.TestContext;

import java.nio.file.Path;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 04/03/14
 * Time: 13:40
 * To change this template use File | Settings | File Templates.
 */
public interface JunitLifeCycleReporter {

    /**
     * Start a test class report
     * @param testClass Class under test
     * @param browserType Browser type under test
     * @param testNameAppendix Additional string to append to the test name
     */
    void testClassStart(Class<?> testClass, String browserType, String testNameAppendix);

    void testClassFinish(TestContext tc);
    void allTestFinished(Result result);

    void testMethodStart(Description description);

    void testMethodFinished(Description description);

    void testIgnored(Description description);

    void testAssumptionFailure(Failure failure);

    void testFailure(Failure failure,String imgPath,String recordingPath,String HtmlSourcePath,String consoleLogPath);


}
