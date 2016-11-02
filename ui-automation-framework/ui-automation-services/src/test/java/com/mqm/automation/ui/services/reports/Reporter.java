package com.mqm.automation.ui.services.reports;

import java.util.Map;

public interface Reporter {
    void info(String message);
    void error(String message);
    void error(String message, Map<String,String> details);
    void warning(String message);
    void debug(String message);
    void testStep(String message);
    void testClassHeader(Map<String,String> headerMapReporter);
}
