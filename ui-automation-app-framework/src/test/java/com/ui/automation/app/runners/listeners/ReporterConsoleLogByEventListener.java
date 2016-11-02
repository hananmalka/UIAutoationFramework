package com.ui.automation.app.runners.listeners;

import com.mqm.automation.ui.services.execution.ApplicationContextHolder;
import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.app.eventBus.TestEventListener;
import com.ui.automation.selenium.browser.BrowserPage;
import org.junit.runner.Description;
import org.junit.runner.notification.RunListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 18/03/14
 * Time: 11:10
 * To change this template use File | Settings | File Templates.
 */
@Component
public class ReporterConsoleLogByEventListener extends RunListener implements TestEventListener {

    @Autowired
    private BrowserPage browser;

    private ReporterConsoleLogByEventListener() {
        // Singleton
    }

    private final static ReporterConsoleLogByEventListener instance = new ReporterConsoleLogByEventListener();

    public static synchronized ReporterConsoleLogByEventListener getInstance() {
        return instance;
    }

    @Override
    public void testStarted(Description description) throws Exception {
        super.testStarted(description);
        writeToBrowserConsole("### TestPages \""+description.getDisplayName() +"\" started ###");
    }

    public void beforeEvent(TestEvent event) {
        writeToBrowserConsole(createConsoleMessageByEvent(event));
    }

    public void afterEvent(TestEvent event) {
    }

    private String createConsoleMessageByEvent(TestEvent event){
        return "["+event.getUuid()+"] ";
    }
    private void writeToBrowserConsole(String text){
        if(browser == null){
            browser = getBrowser();
        }
        browser.consoleLog(text);
    }

    private BrowserPage getBrowser() {
        final BrowserPage browser = ApplicationContextHolder.getApplicationContext().getBean(BrowserPage.class);
        return browser;
    }
}


