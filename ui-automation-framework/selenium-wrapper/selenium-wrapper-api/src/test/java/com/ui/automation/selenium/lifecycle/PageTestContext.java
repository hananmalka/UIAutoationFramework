package com.ui.automation.selenium.lifecycle;

/**
 * Per-thread context for driving test execution.
 * <p/>
 * {@code #setup} is expected to be called <strong>before</strong> starting execution of automation tests on this thread.
 * <p/>
 * {@code #teardown} is expected to be called <strong>after</strong> execution of automation tests on this thread has ended.
 */
public interface PageTestContext {

    /**
     * Setup test context for this thread
     */
    void setup();

    /**
     * Teardown test context.
     * After this call the test context associated to this thread cannot be used any more
     */
    void teardown();
}
