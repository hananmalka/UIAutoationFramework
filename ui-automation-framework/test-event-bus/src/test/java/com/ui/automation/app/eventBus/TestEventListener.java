package com.ui.automation.app.eventBus;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 13/03/14
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
public interface TestEventListener {
    void beforeEvent(TestEvent event);
    void afterEvent(TestEvent event);
}
