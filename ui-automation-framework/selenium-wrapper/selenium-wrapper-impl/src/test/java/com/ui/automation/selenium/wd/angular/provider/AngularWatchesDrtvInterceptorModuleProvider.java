package com.ui.automation.selenium.wd.angular.provider;

import java.util.Collections;
import java.util.List;

// TODO: DANA - disabled because the 'watchesDrtvDecorator' depends on the 'platform-ui' module (see 'watchesDrtvDecorator.js')
//@Component
public class AngularWatchesDrtvInterceptorModuleProvider extends BaseAngularProvider {

    private static final String MODULE_NAME = "watchesDrtvDecorator";

    protected AngularWatchesDrtvInterceptorModuleProvider() {
        super(MODULE_NAME);
    }

    @Override
    protected List<String> getModuleDefinitionParameters() {
        return Collections.emptyList();
    }
}
