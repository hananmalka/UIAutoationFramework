package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.enums.LimitationEnum;
import com.ui.automation.locator.Locator;

public class LimitationRowPanel extends Panel {

    LimitationPanel limitationPanel;
    AlertsPanel alertsPanel;

    public LimitationRowPanel(LimitationEnum limitationEnum, BaseElement parent) {
        super(Locator.xpath("//*[text()='" + limitationEnum.getLabel() + "']//ancestor::*[@class='capping-blocks']/*[@class='row']"), parent);
        limitationPanel = new LimitationPanel(this);
        alertsPanel = new AlertsPanel(this);
    }
}
