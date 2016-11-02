package com.ui.automation.reporter.api;

import com.ui.automation.app.eventBus.TestEvent;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 18/03/14
 * Time: 11:11
 * To change this template use File | Settings | File Templates.
 */
public interface AutoStepReporter {

    /**
     *
     * @param message
     * @param t
     * @param forceWrite - true = force write even if auto step failure occurs and reported
     */
    void stepGeneralFailure(String message, Throwable t,boolean forceWrite);
    void autoStep(TestEvent event);
    void autoStepFailure();
}
