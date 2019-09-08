package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.locator.Locator;

public class RankingPriorityPane extends Pane {

    public PublishersPane publishersPane;

    public RankingPriorityPane(BaseElement parent) {
        super(Locator.id("ranking-priority"), parent);
        publishersPane = new PublishersPane(this);
    }
}
