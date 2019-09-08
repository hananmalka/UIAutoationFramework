package com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.CheckBox;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.FullScreenOfferPane;

public class FullScreenOfferGroup extends Form {

    static FullScreenOfferPane fullScreenOfferPane;

    public FullScreenOfferGroup(BaseElement parent) {
        //TODO: should be a wrapper with locator for when the pane is not visible 'num-row row should be replaced with this wrapper locator
        super(Locator.xpath("//div[contains(@class, 'component-content-wrapper')]//fieldset[3]"), parent);
        fullScreenOfferPane = new FullScreenOfferPane(this);
    }

    public CheckBox checkBoxFSO() {
        return checkBoxField();
    }
}
