package com.ui.automation.elements.entityform;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.base.BaseTopLevelElement;

/**
 * Created by Dana Shalev on 26/11/2015.
 */
public class EntityForm extends BaseTopLevelElement {

    public EntityForm() {
        super(Locator.dataAid("alm-entity-form"));
    }

    public EntityFormHeader header() {
        return new EntityFormHeader(this);
    }

    public EntityFormTab tab(String tabName) {
        return new EntityFormTab(tabName, this);
    }
}
