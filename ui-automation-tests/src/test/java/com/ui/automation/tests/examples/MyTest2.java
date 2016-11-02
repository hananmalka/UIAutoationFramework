package com.ui.automation.tests.examples;

import com.ui.automation.tests.BaseAutomationTest;
import org.junit.Test;
import org.springframework.test.context.TestExecutionListeners;

@TestExecutionListeners(MyTestExecutionListener2.class)
public class MyTest2 extends BaseAutomationTest {

    @Test
    public void TestA() {
        reporter.testStep("MyTest2.TestA - BEGIN");
        System.out.println("Hello from MyTest2:TestA");
        reporter.testStep("MyTest2.TestA - END");
    }

//    @Test
    public void TestB() {
        reporter.testStep("MyTest2.TestB - BEGIN");
        System.out.println("Hello from MyTest2:TestA");
        reporter.testStep("MyTest2.TestB - END");
    }
}