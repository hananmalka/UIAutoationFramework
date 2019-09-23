package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.dropdowns.AutoCompleteDropDown;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

public class PublishersPanel extends Panel {

    public PublishersPanel(BaseElement parent) {
        super(Locator.className("dynamicListContainer"), parent);
    }

    public Button addNewPublisherButton() {
        return buttonField(Locator.xpath("//button[text()[contains(., 'Add new')]]"));
    }

    public TextBox addNewPublisherSearchBox() {
        return textBoxField(Locator.css("input[placeholder=\"Search...\"]"));
    }

    public AutoCompleteDropDown publishersDropDown() {
        return new AutoCompleteDropDown(this);
    }
}
