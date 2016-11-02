package com.ui.automation.tests.examples;

import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.tests.NonStaticBeforeClass;
import org.junit.*;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import java.lang.reflect.Method;

/**
 * Created by Dana Shalev on 22/11/2015.
 */
@TestExecutionListeners(BeforeAfterTestExecutionListener.class)
@ContextConfiguration(locations = {"classpath:/examples/test-context.xml"})
public class BeforeAfterExample extends AbstractJUnit4SpringContextTests {

    @Autowired
    protected Reporter reporter;

    private static boolean beforeClassMethodCalled = false;

    @ClassRule
    public static TestRule classRule = new TestWatcher() {
        @Autowired
        private Reporter reporter;

        @Override
        protected void starting(Description description) {
            System.out.println("classRule.starting: reporter is " + ((reporter == null) ? "NOT " : "") + "available");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("classRule.finished: reporter is " + ((reporter == null) ? "NOT " : "") + "available");
        }
    };

    @Rule
    public TestRule methodRule = new TestWatcher() {

        @Override
        protected void starting(Description description) {
            System.out.println("methodRule.starting: reporter is " + ((reporter == null) ? "NOT " : "") + "available");
        }

        @Override
        protected void finished(Description description) {
            System.out.println("methodRule.finished: reporter is " + ((reporter == null) ? "NOT " : "") + "available");
        }
    };

    @BeforeClass
    public static void beforeClass() {
        System.out.println("beforeClass: reporter is NOT available");
    }

    @NonStaticBeforeClass
    public void nonStaticBeforeClass() {
        System.out.println("nonStaticBeforeClass: reporter is " + ((reporter == null) ? "NOT " : "") + "available");
    }

    @Rule
    public TestRule nonStaticBeforeClass = new TestWatcher() {

        private Method getNonStaticBeforeClassMethod(Class clazz) {
            Method beforeClassMethod = null;
            for (Method method : clazz.getMethods()) {
                if (method.isAnnotationPresent(NonStaticBeforeClass.class)) {
                    beforeClassMethod = method;
                    break;
                }
            }
            return beforeClassMethod;
        }

        @Override
        protected void starting(Description description) {
            if (beforeClassMethodCalled) {
                return;
            }

            Method beforeClassMethod = getNonStaticBeforeClassMethod(BeforeAfterExample.this.getClass());
            try {
                beforeClassMethod.invoke(BeforeAfterExample.this);
            } catch (ReflectiveOperationException e) {
                throw new RuntimeException("Failed executing NonStaticBeforeClass method: " + beforeClassMethod.getName(), e);
            }

            beforeClassMethodCalled = true;
        }
    };

    @AfterClass
    public static void afterClass() {
        System.out.println("afterClass: reporter is NOT available");
    }

    @Before
    public void before() {
        System.out.println("before: reporter is " + ((reporter == null) ? "NOT " : "") + "available");
    }

    @After
    public void after() {
        System.out.println("after: reporter is " + ((reporter == null) ? "NOT " : "") + "available");
    }

    @Test
    public void TestA() {
        System.out.println("TestA");
    }

    @Test
    public void TestB() {
        System.out.println("TestB");
    }
}

class BeforeAfterTestExecutionListener extends AbstractTestExecutionListener {

    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        System.out.println("Listener:beforeTestClass");
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        System.out.println("Listener:afterTestClass");
    }

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {
        System.out.println("Listener:prepareTestInstance");
    }

    @Override
    public void beforeTestMethod(TestContext testContext) throws Exception {
        System.out.println("Listener:beforeTestMethod");
    }

    @Override
    public void afterTestMethod(TestContext testContext) throws Exception {
        System.out.println("Listener:afterTestMethod");
    }
}
