package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

public class LimitationPanel extends Panel {

    public LimitationPanel(BaseElement parent) {
        super(Locator.className("limitation-type"), parent);
    }

    public TextBox limitationTextBox() {
        return textBoxField(Locator.xpath("//span[text()='Limit $']/following-sibling::input"));
    }

    public TextBox bufferTextBox() {
        return textBoxField(Locator.xpath("//span[text()='Buffer %']/following-sibling::input"));
    }
}
