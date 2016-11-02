package com.ui.automation.selenium.wd.angular.provider;

import com.ui.automation.common.angular.ConfigurationServiceMockValuesProvider;
import com.ui.automation.selenium.wd.angular.AngularModuleProvider;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 * Injetcs a new module which allows overriding ConfigurationService properties (SPMaaS Angular service)
 */
// TODO: DANA - disabled provider since this is an applicative MAAS provider that should be removed
//@Component("ConfigurationServiceProvider")
public class ConfigurationServiceProvider implements AngularModuleProvider {

    private static final String MODULE_NAME = "configurationServiceDecorator";

    @Autowired(required = false)
    private List<ConfigurationServiceMockValuesProvider> mockValuesProviders;

    @Override
    public String getModuleName() {
        return MODULE_NAME;
    }

    @Override
    public String getModuleDefinition() {
        // Monkey patch the original configuration service to provide mock values for tests
        return "angular.module('" + MODULE_NAME + "', []).config(['$provide', function ($provide) {\n"
                + "    $provide.decorator('ConfigurationService', function ($delegate) {\n"
                + "         var originalGet = $delegate.get,\n"
                + "             getMock = function (key) {\n"
                // Return mock values for overridden keys
                + getMockValuesJs()
                // If none of the overridden keys match, return the original configuration value for the key
                + "                    return originalGet(key);\n"
                + "             };\n"
                + "         $delegate.get = getMock;\n"
                + "         return $delegate;\n"
                + "    });\n"
                + "}]);\n";
    }

    private String getMockValuesJs() {
        StringBuilder mockedValueJsCode = new StringBuilder();
        for (ConfigurationServiceMockValuesProvider mockValuesProvider : mockValuesProviders) {
            for (Map.Entry<String, String> entry : mockValuesProvider.getMockValues().entrySet()) {
                mockedValueJsCode
                        .append("                    if (key === '" + entry.getKey() + "') {\n")
                        .append("                            return " + entry.getValue() + ";\n")
                        .append("                    }\n");
            }
        }
        return mockedValueJsCode.toString();
    }
}
