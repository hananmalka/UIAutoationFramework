package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.enums.LimitationEnum;
import com.ui.automation.locator.Locator;

public class LimitationRowPane extends Pane {

    LimitationPane limitationPane;
    AlertsPane alertsPane;

    public LimitationRowPane(LimitationEnum limitationEnum, BaseElement parent) {
        super(Locator.xpath("//*[text()='" + limitationEnum.getLabel() + "']//ancestor::*[@class='capping-blocks']/*[@class='row']"), parent);
        limitationPane = new LimitationPane(this);
        alertsPane = new AlertsPane(this);
    }
}
