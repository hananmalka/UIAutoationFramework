package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

public class TrackingPane extends Form {

    public TrackingPane(BaseElement parent) {
        super(Locator.className("tracking panel row"), parent);
    }

    public TextBox installUrlTextBox() {
        return textBoxField(Locator.id("installUrl"));
    }

    public TextBox impressionUrlTextBox() {
        return textBoxField(Locator.id("impressionUrl"));
    }

    public TextBox landingPageValidationTextBox() {
        return textBoxField(Locator.id("validationUrl"));
    }

    public Button autoFillImpressionUrlButton() {
        return buttonField(Locator.id("auto-fill-btn"));
    }
}
