package com.ui.automation.selenium.wd.angular.config;

import com.ui.automation.common.angular.ConfigurationServiceMockValuesProvider;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Sample implementation of ConfigurationServiceMockValuesProvider
 */
@Component
public class PlatformConfigurationServiceMockValuesProvider implements ConfigurationServiceMockValuesProvider {

    private static Map<String, String> mockedValues = new HashMap<>();

    static {
        // Wait for 5 seconds before removing gantt tooltips in testing env
        mockedValues.put("ganttTooltipTimeout", "5000");
        // Wait for 5 seconds before removing gantt tooltips in testing env
        mockedValues.put("workflowToolbarTimeout", "10000");
        // If the tooltip in the test will be closed by clicking.
        mockedValues.put("ganttTooltipCloseByClick", "true");
        // Wait for 15 seconds before giving up on post-commit check after entity updates in testing env
        mockedValues.put("entityDataServicePostCommitTimeout", "15000");
        //wait 2 minutes before removing ess live notification in testing env
        mockedValues.put("essLiveNotificationTimeout", "120000");
        //Wait for 1 millisecond before allowing another click on change calendar's 'show more'
        mockedValues.put("changeCalendarShowMoreDebounceTimeout", "1");
    }

    @Override
    public Map<String, String> getMockValues() {
        return Collections.unmodifiableMap(mockedValues);
    }
}
