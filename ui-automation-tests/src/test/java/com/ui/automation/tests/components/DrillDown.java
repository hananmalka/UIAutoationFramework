package com.ui.automation.tests.components;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.locator.Locator;
import org.springframework.stereotype.Component;

@Component
public class DrillDown {

    private static final String STATUS_ID = "oo-ri-header-content";
    private static final String RESUME_BUTTON_ID = "oo-ri-button";
    private static final String RUN_ID_XPATH = "//span[@data-bind=\"text: runId\"]";

    public void waitUntilPrompt() {
        BaseElement elem = new BaseElement(Locator.id(STATUS_ID), RootElement.getInstance());
        elem.expectVisible();
    }

    public void clickResume() {
        Button resume = new Button(Locator.id(RESUME_BUTTON_ID), RootElement.getInstance());
        resume.click();
    }

    public String getRunID() {
        BaseElement elem = new BaseElement(Locator.xpath(RUN_ID_XPATH), RootElement.getInstance());

        return null;
    }
}
