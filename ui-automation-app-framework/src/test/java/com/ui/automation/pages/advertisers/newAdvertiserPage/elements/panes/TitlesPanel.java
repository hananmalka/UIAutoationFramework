package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.locator.Locator;

public class TitlesPanel extends Panel {

    public TitlesPanel(BaseElement parent) {
        super(Locator.xpath("//*[contains(@class, 'AdvertiserTitles')]//*[@class='panel-body']"), parent);
    }

    public TitleRowPanel titleRowPane(String titlePlaceHolder) {
        return new TitleRowPanel(titlePlaceHolder, this);
    }
}
