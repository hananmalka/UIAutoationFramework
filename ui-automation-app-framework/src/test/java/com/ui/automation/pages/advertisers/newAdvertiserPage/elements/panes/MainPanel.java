package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.CheckBox;
import com.ui.automation.elements.controls.ToggleButton;
import com.ui.automation.elements.controls.dropdowns.StaticDropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

public class MainPanel extends Panel {

    public MainPanel(BaseElement parent) {
        super(Locator.xpath("//*[contains(@class, 'AdvertiserComponent')]//*[text()='General Settings']//ancestor::*//following-sibling::*//*[contains(@class, 'fieldSet')]"), parent);
    }

    public TextBox nameTextBox() {
        return textBoxField(Locator.id("advertiserName"));
    }

    public TextBox salesForceIDTextBox() {
        return textBoxField(Locator.id("salesForceId"));
    }

    public StaticDropDown ownerDropDown() {
        return staticDropDown();
    }

    public CheckBox useReportingApiCheckBox() {
        return checkBoxField(Locator.id("reporting-api-checkbox"));
    }

    public ToggleButton estimatedBidsToggle() {
        return (ToggleButton) checkBoxField(Locator.id("estimated-bids"));
    }

    public ToggleButton installIntentToggle() {
        return (ToggleButton) checkBoxField(Locator.id("install-intent-switch"));
    }

    public ToggleButton launchIntentToggle() {
        return (ToggleButton) checkBoxField(Locator.id("launch-intent-switch"));
    }
}
