package com.ui.automation.selenium.lifecycle;

import com.ui.automation.selenium.wd.MaasDriver;

/**
 * Per-thread context for driving test execution.
 * <p/>
 * {@code #setup} is expected to be called <strong>before</strong> starting execution of automation tests on this thread.
 * <p/>
 * {@code #teardown} is expected to be called <strong>after</strong> execution of automation tests on this thread has ended.
 * <p/>
 * This class returns the {@link MaasDriver} for calling thread.
 */
public interface DriverTestContext {

    /**
     * Setup test context for this thread
     */
    void setup();

    /**
     * Teardown test context.
     * After this call the test context associated to this thread cannot be used any more
     */
    void teardown();

    /**
     * Returns the {@link MaasDriver} associated to this thread if it is setup and was not destoryed yet.
     *
     * @return MaasDriver associated to this execution thread or <code>null</code> if this thread does not have a valid driver
     */
    MaasDriver getDriver();
}
