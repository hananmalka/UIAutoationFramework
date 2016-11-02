package com.ui.automation.selenium.wd.angular;

import com.ui.automation.common.element.config.TestProperties;
import com.ui.automation.selenium.lifecycle.DriverTestContext;
import com.ui.automation.selenium.wd.DriverServices;
import com.ui.automation.selenium.wd.MaasDriverImpl;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 */
@Component
public class ClientSideScripts {

    @Autowired
    private List<AngularModuleProvider> moduleProviders;

    @Autowired
    private TestProperties testProperties;

    @Autowired
    private DriverTestContext driverTestContext;

    private final Logger logger = Logger.getLogger(getClass().getName());

    public void deferBootstrap() {
        logger.info("Deferring Angular bootstrap");
        DriverServices driverServices = ((MaasDriverImpl)driverTestContext.getDriver()).getDriverServices();
        driverServices.executeJavaScript("window.name = 'NG_DEFER_BOOTSTRAP!' + window.name;");
    }

    public void resumeBootstrap() {
        final DriverServices driverServices = ((MaasDriverImpl)driverTestContext.getDriver()).getDriverServices();

        // Check that we passed HPSSO
        // If MaaS app is not loaded or we're in the help iframe then there's nothing to resume
        // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//        if (!isMaasAppLoaded() || isHelpUrl()) {
//            return;
//        }

        // check that angular is up
        driverServices.getFluentWait().until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(org.openqa.selenium.WebDriver webDriver) {
                boolean hasAngular = isAngularDefined();
                // For logout race conditions where the browser url still indicates that the app is loaded but the underlying DOM does not contain Angular
                boolean isAppLoaded = isMaasAppLoaded();
                // Complete this wait task if angular has loaded OR if we're not expecting it
                return hasAngular || !isAppLoaded;
            }
        });

        // Check MaaS app is loaded (again, since the browser url changes async)
        if (isMaasAppLoaded()) {
            // Check that angular is still pending manual bootstrap
            if (isPendingBootstrap()) {
                logger.info("Resuming angular bootstrap");
                resumeBootstrapInternal();
            }
        }
    }

    private void resumeBootstrapInternal() {
        final DriverServices driverServices = getDriverServices();
        // Resume bootstrap (this clears the DEFER_BOOTSTRAP token from the window name by Angular)
        List<String> moduleNames = new ArrayList<>(moduleProviders.size());
        for (AngularModuleProvider moduleProvider : moduleProviders) {
            driverServices.executeJavaScript(moduleProvider.getModuleDefinition());
            moduleNames.add(moduleProvider.getModuleName());
        }
        driverServices.executeJavaScript("angular.resumeBootstrap(arguments[0]);", moduleNames);
        // Nullify resumeBootstrap so we know that Angular is bootstrapped. Sorry Misko ;)
        driverServices.executeJavaScript("delete angular.resumeBootstrap");
    }

    private boolean isPendingBootstrap() {
        try {
            return (Boolean) getDriverServices().executeJavaScript("return angular.resumeBootstrap !== undefined");
        } catch (Exception e) {
            logger.severe("Failed to check if pending bootstrap. " +
            "Browser URL = " + getDriverServices().getDriver().getCurrentUrl().toLowerCase() +
            " isAngularDefined = " + isAngularDefined());
            throw e;
        }
    }

    private boolean isMaasAppLoaded() {
        // TODO: DANA - check whether we need this method (as isMQMAppLoaded) - in the meantime return true
        //return getDriverServices().getDriver().getCurrentUrl().toLowerCase().contains(testProperties.getAppBaseUrl().toLowerCase());
        return true;
    }

    // TODO: DANA - remarked in order to remove dependency in maas-automation-tools (see if we want to replace with our own implementation)
//    private boolean isHelpUrl() {
//        final String helpUrl = "/help/" + testProperties.getTestLocaleUiFormatString();// + "/embedded";
//        return getDriverServices().getDriver().getCurrentUrl().toLowerCase().contains(helpUrl);
//    }

    private boolean isAngularDefined() {
        return (Boolean) getDriverServices().executeJavaScript("return window.angular !== undefined");
    }

    private DriverServices getDriverServices() {
        return ((MaasDriverImpl)driverTestContext.getDriver()).getDriverServices();
    }
}
