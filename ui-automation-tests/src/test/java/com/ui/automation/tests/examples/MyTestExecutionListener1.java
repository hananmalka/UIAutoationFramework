package com.ui.automation.tests.examples;

import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Created by Dana Shalev on 15/11/2015.
 */
public class MyTestExecutionListener1 extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        System.out.println("MyTestExecutionListener1:beforeTestClass");
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        System.out.println("MyTestExecutionListener1:afterTestClass");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        System.out.println("MyTestExecutionListener1:prepareTestInstance");
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        System.out.println("MyTestExecutionListener1:beforeTestMethod");
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        System.out.println("MyTestExecutionListener1:afterTestMethod");
    }
}
