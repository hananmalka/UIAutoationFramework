package com.ui.automation.selenium.wd.angular.provider;

import java.util.Collections;
import java.util.List;

/**
 * Invalidates waitForAngular waiting on $timeout by decorating $browser service so that calls to $timeout
 * won't affect outstandingRequestCount in angular.js
 */
// Comment out as Spring bean - testing if this is the cause for instability in CI
//@Component
public class AngularBrowserProvider extends BaseAngularProvider {

    private static final String MODULE_NAME = "browserDecorator";

    protected AngularBrowserProvider() {
        super(MODULE_NAME);
    }

    @Override
    protected List<String> getModuleDefinitionParameters() {
        return Collections.emptyList();
    }
}
