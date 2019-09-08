package com.ui.automation.common.angular;

import java.util.Map;

/**
 * This class allows for overriding values defined in configuration-prdr.js
 */
public interface ConfigurationServiceMockValuesProvider {
    /**
     * Returns a key-value map where the keys are the ones defined in the applications ConfigurationService.
     * The provided value for each key will override the one defined in the service.
     * @return key-value map for keys to override in the application's ConfigurationService
     */
    Map<String, String> getMockValues();
}
