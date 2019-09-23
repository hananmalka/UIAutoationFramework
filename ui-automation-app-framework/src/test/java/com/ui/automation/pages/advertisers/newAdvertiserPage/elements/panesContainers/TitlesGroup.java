package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes.TitleRowPanel;

public class TitlesGroup extends Form {

    public TitlesGroup(BaseElement parent) {
        super(Locator.className("AdvertiserTitles"), parent);
    }

    public Button addTitleButton() {
        return buttonField(Locator.id("advertiser-titles-add-group-btn"));
    }

    public TitleRowPanel newTitleRowPane() {
        return new TitleRowPanel("Paste Package Name", this);
    }

    public TitleRowPanel existTitleRowPane(String placeholder) {
        return new TitleRowPanel(placeholder, this);
    }
}
