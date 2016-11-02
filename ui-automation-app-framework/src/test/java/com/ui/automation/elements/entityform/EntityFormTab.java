package com.ui.automation.elements.entityform;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

/**
 * Created by Dana Shalev on 26/11/2015.
 */
public class EntityFormTab extends BaseElement {

    public EntityFormTab(String tabName, Element parent) {
        super(Locator.dataAid("mqm-tab-" + tabName), parent);
    }

    public EntityFormTab select() {
        getDriver().actions().click(this);
        return this;
    }
}
