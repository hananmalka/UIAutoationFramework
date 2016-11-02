package com.ui.automation.common.junit;

import org.springframework.stereotype.Component;

/**
 * Returns per-thread test details
 */
@Component
public class TestDetailsHolder {

    private final static ThreadLocal<TestDetails> THREAD_VARIABLES = new ThreadLocal<TestDetails>() {
        @Override
        protected TestDetails initialValue() {
            return new TestDetails();
        }
    };

    public TestDetails getTestDetails() {
        return THREAD_VARIABLES.get();
    }
}
