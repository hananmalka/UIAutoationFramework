package com.ui.automation.reporter.api;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 04/03/14
 * Time: 10:43
 * To change this template use File | Settings | File Templates.
 */
public enum TestStatus {
    FAILED (1),
    IGNORED(2),
    ASSUMPTION_FAILURE(3),
    UNSTABLE_SUCCEED(4),
    UNSTABLE_FAILED(5),
    SUCCEED(6);

    private final int size;

    TestStatus(int i) {
        this.size = i;
    }

    public int getSize() {
        return size;
    }
}
