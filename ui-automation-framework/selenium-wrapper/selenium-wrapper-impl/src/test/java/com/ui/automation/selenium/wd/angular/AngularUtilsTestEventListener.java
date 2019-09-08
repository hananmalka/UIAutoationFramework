package com.ui.automation.selenium.wd.angular;

import com.ui.automation.app.eventBus.TestEvent;
import com.ui.automation.app.eventBus.TestEventListener;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.MaasDriverImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by peere on 09/04/2014.
 */
@Component
public class AngularUtilsTestEventListener implements TestEventListener {

    @Autowired
    private DriverTestContext driverTestContext;

    @Autowired
    private ClientSideScripts clientSideScripts;

    @Override
    public void beforeEvent(TestEvent event) {
        // Set UUID on window object in the browser for correlation with server logs
        // window.correlationId = <event.getUuid()>
        ((MaasDriverImpl)driverTestContext.getDriver()).getDriverServices().executeJavaScript("window.correlationId = '" + event.getUuid() + "'");

        // Resume bootstrap if case it wasn't called yet
        clientSideScripts.resumeBootstrap();
    }

    @Override
    public void afterEvent(TestEvent event) {

    }
}
