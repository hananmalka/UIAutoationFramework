package com.ui.automation.pages.campaigns.newCampaginPage.elements.panelsContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.PublishersPanel;

public class PlacementAllocationGroup extends Form {

    static PublishersPanel placementAllocationPublishersPanel;

    public PlacementAllocationGroup(BaseElement parent) {
        super(Locator.id("placement-allocation"), parent);
        placementAllocationPublishersPanel = new PublishersPanel(this);
    }

}
