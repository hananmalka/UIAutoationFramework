package com.ui.automation.reporter.impl;

import com.ui.automation.reporter.api.TestDoc;
import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.reporter.api.StepLogType;
import org.junit.Ignore;
import org.junit.experimental.categories.Category;
import org.junit.runner.Description;
import org.junit.runner.notification.Failure;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

import static com.ui.automation.app.eventBus.TestEvent.IS_FAILED;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 03/03/14
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
@Component
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TestClassAggregator {

    private final String DATE_FORMAT = "DD/MM/YY HH:mm:ss";
    private final String HOUR_FORMAT = "HH:mm:ss";


    Date classStartTime = null;
    Date classEndTime = null;
    String classFullName = "";
    String className = "";
    Boolean isClassIgnored = false;
    String classIgnoreValue = "";


    Map<String, String> classHeaderVariables = new HashMap<>();
    String classTestDocName = "";
    String classTestDocDescription = "";
    String classCategory = "";
    long totalExecutionSecTime = 0;
    String browser = "";
    TestMethodDetails beforeClass = new TestMethodDetails(CssProperties.BEFORE_CLASS_TXT.value());
    TestMethodDetails afterClass = new TestMethodDetails(CssProperties.AFTER_CLASS_TXT.value());

    List<TestMethodDetails> tests = new ArrayList<>();
    private TestMethodDetails currentTest = null;


    public void reportClassStart(Class<?> clazz, String browserType, String testNameAppendix) {

        this.browser = browserType;
        this.classStartTime = new Date();
        this.classFullName = clazz.getName();
        this.className = clazz.getSimpleName() + " " + testNameAppendix;
        TestDoc tdoc = clazz.getAnnotation(TestDoc.class);
        if (tdoc != null) {
            this.classTestDocName = tdoc.name();
            this.classTestDocDescription = tdoc.description();
        }
        Category category = clazz.getAnnotation(Category.class);
        if (category != null) {
            this.classCategory = category.value()[0].getSimpleName();
        }

        //TODO: add more stuff like is Auto login, is localized, localized file, etc...
    }

    public void reportClassIgnored(Description description) {
        isClassIgnored = true;
        Class<?> clazz = description.getTestClass();
        this.classFullName = clazz.getName();
        this.className = clazz.getSimpleName();
        this.classStartTime = new Date();
        this.classEndTime = new Date();
        TestDoc tdoc = clazz.getAnnotation(TestDoc.class);
        if (tdoc != null) {
            this.classTestDocName = tdoc.name();
            this.classTestDocDescription = tdoc.description();
        }
        Category category = clazz.getAnnotation(Category.class);
        if (category != null) {
            this.classCategory = category.value()[0].getSimpleName();
        }
        Ignore ignore = clazz.getAnnotation(Ignore.class);
        if (ignore != null) {
            this.classIgnoreValue = ignore.value();
        }
    }


    public void reportTestStart(Description description) {
        currentTest = new TestMethodDetails();
        currentTest.testStart(description);
    }

    public void reportTestEnd(Description description) {
        this.currentTest.testEnd(description);
        addCurrentTestToList();

    }

    public void reportClassEnd() {
        this.classEndTime = new Date();
        this.totalExecutionSecTime = (classEndTime.getTime() - classStartTime.getTime()) / 1000;

    }

    public void reportStep(StepLogType type, String message) {

        TestMethodDetails workingMethod = getWorkingMethod();
        if(workingMethod!=null){
            workingMethod.messages.add(new ReportMessage(type, message));
        }

    }

    public void reportAutoStepFailure() {
        TestMethodDetails workingMethod = getWorkingMethod();
        if(workingMethod!=null){
            workingMethod.isAutoStepFailed = true;

            setTestFailureInternal(workingMethod);
        }
    }

    private void setTestFailureInternal(TestMethodDetails tmd){
        if(tmd.name.equals(CssProperties.BEFORE_CLASS_TXT.value()) || tmd.name.equals(CssProperties.AFTER_CLASS_TXT.value()) ){
            this.reportClassFailure(new TestFailureHolder());
        }
    }

    public void reportDetailedStep(StepLogType type, String message, Map<String, String> details) {

        TestMethodDetails workingMethod = getWorkingMethod();
        if(workingMethod!=null){
            workingMethod.messages.add(new ReportMessage(type, message, details));
        }
    }

    public void reportExceptionStep(String message, Throwable t, boolean forceWrite) {

        TestMethodDetails workingMethod = getWorkingMethod();
        if(workingMethod!=null){
            if(forceWrite || !workingMethod.isAutoStepFailed) {
                Map<String, String> details = new HashMap<>();

                details.put(TestEvent.EXC_OCCURS, "<br><b>" + String.valueOf(t.getMessage()) + "</b>");
             //   workingMethod.status = TestStatus.FAILED;

                details.put(TestEvent.EXC_STACK_TRACE,prettifyExceptionHTML(t));
                details.put(TestEvent.IS_FAILED,String.valueOf(true));
                workingMethod.messages.add(new ReportMessage(StepLogType.GENERAL_ERROR, message, details));
                setTestFailureInternal(workingMethod);
            }
        }
    }

    private String prettifyExceptionHTML(Throwable t) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        t.printStackTrace(pw);
        String stackTrace = sw.toString();
        return "<br>" + stackTrace.replace(System.getProperty("line.separator"), "<br/>\n").replace("\tat","&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;at");
    }

    public void reportAutoStep(TestEvent event) {

       TestMethodDetails workingMethod = getWorkingMethod();
        if(workingMethod != null){
            workingMethod.messages.add(new ReportMessage(StepLogType.AUTOSTEP, event.getType().value() + " : " + event.getMessage(), event.getDetails(), event.getUuid()));
        }
    }

    private TestMethodDetails getWorkingMethod(){
        if (tests.size() == 0 && currentTest == null) {
            return beforeClass;
        }
        // case that test is currently running
        else if (currentTest != null) {
            return currentTest;
        }
        // case that tests in class finished and at least one reported
        else if (currentTest == null && tests.size() > 0) {
            return afterClass;
        }
        else return  null;
    }


    private boolean checkAutoStepStatus(TestEvent event, TestMethodDetails testMethodDetails) {
        if (testMethodDetails.isAutoStepFailed) {
            return true;
        } else {
            return event.getDetails().containsKey(IS_FAILED);
        }
    }

    public void reportClassFailure(TestFailureHolder testFailureHolder) {
        if (tests.size() == 0) {
            beforeClass.testFailure(testFailureHolder);
        } else {
            afterClass.testFailure(testFailureHolder);
        }
    }

    public void reportMethodFailure(TestFailureHolder failureHolder) {
        this.currentTest.testFailure(failureHolder);
    }


    public void reportTestIgnored(Description description) {
        currentTest = new TestMethodDetails();
        currentTest.testIgnored(description);
        addCurrentTestToList();

    }

    public void reportTestAssumptionFailure(Failure failure) {
        if (this.currentTest == null) {
            currentTest = new TestMethodDetails();
        }
        currentTest.testAssumptionFailure(failure);
    }

    public void addClassHeaderVariables(Map<String, String> map) {
        this.classHeaderVariables.putAll(map);
    }

    private void addCurrentTestToList() {
        this.tests.add(this.currentTest);
        this.currentTest = null;
    }


}
