package com.ui.automation.tests.examples;

import com.ui.automation.tests.BaseAutomationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.springframework.test.context.TestExecutionListeners;

@RunWith(Suite.class)
@Suite.SuiteClasses({MyTest1.class, MyTest2.class})
@TestExecutionListeners(MyTestExecutionListenerSuite1.class)
public class MySuite1 extends BaseAutomationTest {
}