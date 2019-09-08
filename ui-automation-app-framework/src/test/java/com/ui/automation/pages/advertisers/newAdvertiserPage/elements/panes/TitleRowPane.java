package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.dropdowns.StaticDropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

public class TitleRowPane extends Pane {

    public TitleRowPane(String placeholder, BaseElement parent) {
        super(Locator.xpath("//input[@placeholder='" + placeholder + "']//ancestor::*[contains(@class, 'list-group-item')]"), parent);
    }

    public TextBox packageNameTextBox() {
        return textBoxField(Locator.css("input"));
    }

    public Button scrapeButton() {
        return buttonField(Locator.className("scrapeButton"));
    }

    public StaticDropDown trackingPartnerDropDown() {
        return staticDropDown();
    }

    public BaseElement trashIcon() {
        return new BaseElement(Locator.className("glyphicon"), this);
    }
}
