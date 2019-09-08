package com.ui.automation.selenium.lifecycle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of {@link PageTestContext}
 */
@Component
public class PageTestContextImpl implements PageTestContext {

    @Autowired
    private DriverTestContext driverTestContext;

    public void setup() {
        driverTestContext.setup();
    }

    public void teardown() {
        driverTestContext.teardown();
    }
}
