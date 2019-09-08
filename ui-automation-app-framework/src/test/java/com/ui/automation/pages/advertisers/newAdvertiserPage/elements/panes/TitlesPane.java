package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.locator.Locator;

public class TitlesPane extends Pane {

    public TitlesPane(BaseElement parent) {
        super(Locator.xpath("//*[contains(@class, 'AdvertiserTitles')]//*[@class='panel-body']"), parent);
    }

    public TitleRowPane titleRowPane(String titlePlaceHolder) {
        return new TitleRowPane(titlePlaceHolder, this);
    }
}
