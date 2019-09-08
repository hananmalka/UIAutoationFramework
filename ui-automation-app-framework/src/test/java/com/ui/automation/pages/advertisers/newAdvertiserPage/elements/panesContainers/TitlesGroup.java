package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.TitleRowPane;

public class TitlesGroup extends Form {

    public TitlesGroup(BaseElement parent) {
        super(Locator.className("AdvertiserTitles"), parent);
    }

    public Button addTitleButton() {
        return buttonField(Locator.id("advertiser-titles-add-group-btn"));
    }

    public TitleRowPane newTitleRowPane() {
        return new TitleRowPane("Paste Package Name", this);
    }

    public TitleRowPane existTitleRowPane(String placeholder) {
        return new TitleRowPane(placeholder, this);
    }
}
