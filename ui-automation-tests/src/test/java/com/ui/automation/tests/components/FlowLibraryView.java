package com.ui.automation.tests.components;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.TextBox;
import com.ui.automation.elements.impl.RootElement;
import com.ui.automation.locator.Locator;
import org.springframework.stereotype.Component;

@Component
public class FlowLibraryView {

    private static String ID = "flowLibraryContainer";

    private static final String LIB_VIEW_ID = "flowLibraryTreeContainer_flowLibraryView";

    private static final String FILTER_TEXT_BOX_ID = "filterFlowString";

    private static final String TRIGGER_FLOW_BUTTON_ID = "fl-trigger-flow-button";
    private static final String RUN_FLOW_BUTTON_ID = "ft-dialog-run-button";

    public void selectPath(String... path) {

        String last = path[path.length - 1];

        String xpath = "//*[@id=\"" + LIB_VIEW_ID + "\"]/div/div/div/div[span[5]=\"" + last + "\"]";
        BaseElement element = new BaseElement(Locator.xpath(xpath), RootElement.getInstance());
        element.clickOn();
    }

    public void clickTriggerFlowButton() {
        Button trigger = new Button(Locator.id(TRIGGER_FLOW_BUTTON_ID), RootElement.getInstance());
        trigger.click();
        PauseUtil.waitUntilPageLoaded();
    }

    public void clickRunFlowButton() {
        Button trigger = new Button(Locator.id(RUN_FLOW_BUTTON_ID), RootElement.getInstance());
        trigger.click();
        PauseUtil.waitUntilPageLoaded();
    }

    public void filter(String text) {
        TextBox filterBox = new TextBox(Locator.id(FILTER_TEXT_BOX_ID), RootElement.getInstance());
        filterBox.setValue(text);
        PauseUtil.waitUntilPageLoaded();
    }
}
