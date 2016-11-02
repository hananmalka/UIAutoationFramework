package com.ui.automation.app.listeners;

import com.ui.automation.common.junit.TestDetailsHolder;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

/**
 * Created by peere on 18/12/2014.
 */
public class MaasTestContextListener extends AbstractTestExecutionListener {


    @Override
    public void beforeTestClass(TestContext testContext) throws Exception {
        super.beforeTestClass(testContext);
        TestDetailsHolder testDetailsHolder = getTestDetailsHolder(testContext);
        testDetailsHolder.getTestDetails().setTestName(testContext.getTestClass().getName());
    }

    @Override
    public void afterTestClass(TestContext testContext) throws Exception {
        super.afterTestClass(testContext);
        TestDetailsHolder testDetailsHolder = getTestDetailsHolder(testContext);
        testDetailsHolder.getTestDetails().setTestName(null);
    }

    private TestDetailsHolder getTestDetailsHolder(TestContext testContext) {
        return testContext.getApplicationContext().getBean(TestDetailsHolder.class);
    }
}
