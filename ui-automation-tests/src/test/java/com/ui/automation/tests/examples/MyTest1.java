package com.ui.automation.tests.examples;

import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

//@TestExecutionListeners(MyTestExecutionListener1.class)
public class MyTest1 {//extends BaseAutomationTest {

    @ClassRule
    public static TestRule classRule = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("classRule.starting");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("classRule.finished");
        }
    };

    @Rule
    public TestRule methodRule = new TestWatcher() {
        @Override
        protected void starting(Description description) {
            System.out.println("methodRule.starting");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("methodRule.finished");
        }

    };

    @BeforeClass
    public static void beforeClass() {
        System.out.println("MyTest1:beforeClass");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("MyTest1:afterClass");
    }

    @Test
    public void TestA() {
        System.out.println("MyTest1:TestA");
    }

    @Test
    public void TestB() {
        System.out.println("MyTest1:TestB");
    }
}