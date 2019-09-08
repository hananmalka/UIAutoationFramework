package com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.RankingPriorityPane;

public class RankingPriorityGroup extends Form {

    static RankingPriorityPane rankingPriorityPane;

    public RankingPriorityGroup(BaseElement parent) {
        super(Locator.id("ranking-priority"), parent);
        rankingPriorityPane = new RankingPriorityPane(this);
    }
}
