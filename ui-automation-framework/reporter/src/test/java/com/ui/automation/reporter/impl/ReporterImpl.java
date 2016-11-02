package com.ui.automation.reporter.impl;

import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.reporter.api.AutoStepReporter;
import com.ui.automation.reporter.api.JunitLifeCycleReporter;
import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.recorder.api.TestRecorder;
import com.ui.automation.reporter.api.StepLogType;
import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.test.context.TestContext;

import java.util.Map;
import java.util.logging.Logger;


/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 01/03/14
 * Time: 22:55
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ReporterImpl implements JunitLifeCycleReporter, Reporter, AutoStepReporter, ApplicationContextAware {

    /**
     * Thread local map with this thread's run listener (at most 1 per thread)
     */
    private final static ThreadLocal<TestClassAggregator> CLASS_REPORTERS = new ThreadLocal<TestClassAggregator>() {
    };

    private ApplicationContext applicationContext;

    @Autowired
    TestRecorder testRecorder;

    private Logger logger = Logger.getLogger(getClass().getName());

    private TestClassAggregator getClassReporter() {
        return CLASS_REPORTERS.get();
    }

    private TestClassAggregator createClassReporter() {
        TestClassAggregator testClassAggregator = applicationContext.getBean(TestClassAggregator.class);
        CLASS_REPORTERS.set(testClassAggregator);
        logger.info("Created TestClassAggregator " + testClassAggregator + " for thread " + Thread.currentThread().getName());
        return testClassAggregator;
    }


    @Override
    public void testClassStart(Class<?> testClass, String browserType, String testNameAppendix) {
        // Create a new report for class that is about to run
        logger.info("INFO: Tests Class Start: " + testClass.getSimpleName());
        TestClassAggregator classAggregator = createClassReporter();
        classAggregator.reportClassStart(testClass, browserType, testNameAppendix);

    }

    @Override
    public void testIgnored(Description description) {
        // logger.info("INFO: Test Ignored: class:" + description.getClassName() +  " method:" + description.getMethodName());
        if (description.getMethodName() == null) {
            logger.info("INFO: Test Class Ignored: " + description.getClassName());
            TestClassAggregator classAggregator = createClassReporter();
            classAggregator.reportClassIgnored(description);
            HtmlClassGenerator htmlGenerator = applicationContext.getBean(HtmlClassGenerator.class);
            htmlGenerator.createClassReport(classAggregator);
        } else {
            TestClassAggregator classAggregator = getClassReporter();
            logger.info("INFO: Test Method Ignored: " + description.getMethodName());
            classAggregator.reportTestIgnored(description);
        }
    }

    @Override
    public void testAssumptionFailure(Failure failure) {
        logger.info("INFOAssumptionFailure: class:" + failure.getDescription().getClassName() + " method:" + failure.getDescription().getMethodName());
        if (failure.getDescription().getMethodName() == null) {
            logger.info("--- Test Class Assumption Failure: " + failure.getDescription().getClassName());
            //TODO: currently we don't have a case of class assumption failure. Implement as needed.
            /*TestClassAggregator classAggregator = createClassReporter();
            classAggregator.reportClassAssumptionFailure(failure);
            HtmlClassGenerator htmlGenerator = applicationContext.getBean(HtmlClassGenerator.class);
            htmlGenerator.createClassReport(classAggregator);*/
        } else {
            TestClassAggregator classAggregator = getClassReporter();
            logger.info("--- Test Method Assumption Failure: " + failure.getDescription().getMethodName());
            classAggregator.reportTestAssumptionFailure(failure);
        }
    }


    @Override
    public void testClassFinish(TestContext result) {
        logger.info("--- Test Class Run Finished");
        final TestClassAggregator classAggregator = getClassReporter();
        classAggregator.reportClassEnd();
        HtmlClassGenerator htmlGenerator = applicationContext.getBean(HtmlClassGenerator.class);
        htmlGenerator.createClassReport(classAggregator);
    }

    @Override
    public void allTestFinished(Result result) {
        String prefix = "successfully";
        if (!result.wasSuccessful()) {
            prefix = "with failures";
        }
        logger.info("-- Tests Finished " + prefix + "!! Total: " + result.getRunCount() + ", Failed: " + result.getFailureCount() + ", Ignored: " + result.getIgnoreCount());
    }


    @Override
    public void testMethodStart(Description description) {
        logger.info("--- Test Method Start: " + description.getMethodName() + " Thread " + Thread.currentThread().getName());
        getClassReporter().reportTestStart(description);
    }

    @Override
    public void testMethodFinished(Description description) {
        logger.info("--- Test Method Finish: " + description.getMethodName() + " Thread " + Thread.currentThread().getName());
        getClassReporter().reportTestEnd(description);
    }


    @Override
    public void testFailure(Failure failure, String imgPath, String recordingPath, String htmlSourcePath, String consoleLogPath) {

        TestClassAggregator classAggregator = getClassReporter();
        TestFailureHolder tfh = new TestFailureHolder(failure, imgPath, consoleLogPath, recordingPath, htmlSourcePath);
        if (failure.getDescription().getMethodName() != null) {
            classAggregator.reportMethodFailure(tfh);
            logger.info("--- Test Method Finished with Failure: " + failure.getDescription().getMethodName());
        } else {
            classAggregator.reportClassFailure(tfh);
            logger.info("--- Test Class Finished with Failure: " + failure.getDescription().getClassName());
        }
    }

    /**
     * Internal use only
     */
    @Override
    public void autoStepFailure() {
        logger.info("-- Test Auto Step Failure Occur");
        TestClassAggregator classAggregator = getClassReporter();
        classAggregator.reportAutoStepFailure();
    }

    /**
     * Internal use only
     */
    @Override
    public void stepGeneralFailure(String message, Throwable t,boolean forceWrite) {
        logger.info("-- Test Step General Failure Occur");
        TestClassAggregator classAggregator = getClassReporter();
        classAggregator.reportExceptionStep(message,t,forceWrite);
    }

    @Override
    public void testClassHeader(Map<String, String> headerMapReporter) {
        getClassReporter().addClassHeaderVariables(headerMapReporter);
    }

    @Override
    public void info(String message) {
        logger.info("[REPORT INFO] " + message);
        getClassReporter().reportStep(StepLogType.INFO, message);
        testRecorder.updateRecording(null, null, "[INFO] " + message);
    }

    @Override
    public void error(String message) {
        logger.info("[REPORT ERROR] " + message);
        getClassReporter().reportStep(StepLogType.ERROR, message);
        testRecorder.updateRecording(null, null, "[ERROR] " + message);
    }

    @Override
    public void error(String message, Map<String, String> details) {
        logger.info("[REPORT ERROR] " + message);
        getClassReporter().reportDetailedStep(StepLogType.ERROR, message, details);
    }


    @Override
    public void warning(String message) {
        logger.info("[REPORT WARNING] " + message);
        getClassReporter().reportStep(StepLogType.WARNING, message);
        testRecorder.updateRecording(null, null, "[WARNING] " + message);
    }

    @Override
    public void debug(String message) {
        logger.info("[REPORT DEBUG] " + message);
        getClassReporter().reportStep(StepLogType.DEBUG, message);
    }


    @Override
    public void testStep(String message) {
        logger.info("[REPORT TEST STEP] " + message);
        getClassReporter().reportStep(StepLogType.TESTSTEP, message);
        testRecorder.updateRecording(null, "Test step: " + message, null);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void autoStep(TestEvent event) {
        getClassReporter().reportAutoStep(event);
        testRecorder.updateRecording(null, null, event.getType().value() + " : " + event.getMessage());
    }




}
