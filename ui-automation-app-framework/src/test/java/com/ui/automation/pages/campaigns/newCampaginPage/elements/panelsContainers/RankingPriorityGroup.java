package com.ui.automation.pages.campaigns.newCampaginPage.elements.panelsContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panels.RankingPriorityPanel;

public class RankingPriorityGroup extends Form {

    static RankingPriorityPanel rankingPriorityPanel;

    public RankingPriorityGroup(BaseElement parent) {
        super(Locator.id("ranking-priority"), parent);
        rankingPriorityPanel = new RankingPriorityPanel(this);
    }
}
