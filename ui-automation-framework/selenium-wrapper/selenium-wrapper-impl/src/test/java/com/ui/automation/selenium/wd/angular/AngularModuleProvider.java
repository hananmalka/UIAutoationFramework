package com.ui.automation.selenium.wd.angular;

/**
 * Created by peere on 08/04/2014.
 */
public interface AngularModuleProvider {

    /**
     * Returns the module name
     * @return
     */
    String getModuleName();

    /**
     * Returns the module definition as a String that is injected to the browser in order to create a new Angular module.
     * @return
     */
    String getModuleDefinition();
}
