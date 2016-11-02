package com.ui.automation.tests;

import com.mqm.automation.ui.services.reports.Reporter;
import com.ui.automation.app.listeners.BrowserManagerListener;
import com.ui.automation.app.listeners.MaasTestContextListener;
import com.ui.automation.app.listeners.ReportCreationListener;
import com.ui.automation.app.listeners.ReportFlushListener;
import com.ui.automation.app.runners.SpringJUnit4ClassReporterRunner;
import com.ui.automation.tests.components.DrillDown;
import com.ui.automation.tests.components.FlowLibraryView;
import com.ui.automation.tests.components.OOMainApp;
import com.ui.automation.tests.components.RunView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@RunWith(SpringJUnit4ClassReporterRunner.class)
@TestExecutionListeners(listeners = {ReportCreationListener.class, MaasTestContextListener.class, BrowserManagerListener.class, ReportFlushListener.class})
@ContextConfiguration(locations = {"classpath:/META-INF/spring/ui-automation-tests-context.xml"})
public class OOBaseTest extends AbstractJUnit4SpringContextTests {

    private final String USERNAME = "q";
    private final String PASSWORD = "q";
    private final String OO_URL = "http://localhost:8080/oo";

    static {
        System.setProperty("reportAutoOpen", "false");
    }

    @Autowired
    protected Reporter reporter;

    @Autowired
    private OOMainApp mainApp;

    @Autowired
    private FlowLibraryView flowLibraryView;

    @Autowired
    private DrillDown drillDown;

    @Autowired
    private RunView runView;

    @Test
    public void triggerFlow() {

        mainApp.goToOO(OO_URL);

        mainApp.login(USERNAME, PASSWORD);

        mainApp.goToFlowLibrary();

        flowLibraryView.filter("How do I- Create a parallel flow");

        flowLibraryView.selectPath("Library", "How Do I flows", "How do I- Create a parallel flow");

        flowLibraryView.clickTriggerFlowButton();

        flowLibraryView.clickRunFlowButton();

        drillDown.waitUntilPrompt();

        drillDown.clickResume();

        mainApp.goToRunView();

        System.out.println("runID" + runView.getLastRunID());
        System.out.println("status" + runView.getRunStatusLastRun());

        mainApp.logout();

        mainApp.closeBrowser();

        System.out.println("end");
        System.out.println("==== end ====");
    }
}
