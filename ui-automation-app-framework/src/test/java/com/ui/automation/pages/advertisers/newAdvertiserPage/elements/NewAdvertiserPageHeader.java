package com.ui.automation.pages.advertisers.newAdvertiserPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.header.Header;
import com.ui.automation.locator.Locator;

public class NewAdvertiserPageHeader extends Header {

    public NewAdvertiserPageHeader(Element parent) {
        super(parent);
    }

    public Button saveButton() {
        return headerButton(Locator.id("main-button-save"));
    }
}
