package com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.PublishersPane;

public class PlacementAllocationGroup extends Form {

    static PublishersPane placementAllocationPublishersPane;

    public PlacementAllocationGroup(BaseElement parent) {
        super(Locator.id("placement-allocation"), parent);
        placementAllocationPublishersPane = new PublishersPane(this);
    }

}
