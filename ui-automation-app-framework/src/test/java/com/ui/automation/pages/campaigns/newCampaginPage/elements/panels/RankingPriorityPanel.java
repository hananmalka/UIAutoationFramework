package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.locator.Locator;

public class RankingPriorityPanel extends Panel {

    public PublishersPanel publishersPanel;

    public RankingPriorityPanel(BaseElement parent) {
        super(Locator.id("ranking-priority"), parent);
        publishersPanel = new PublishersPanel(this);
    }
}
