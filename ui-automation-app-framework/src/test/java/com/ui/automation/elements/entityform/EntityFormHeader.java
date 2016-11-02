package com.ui.automation.elements.entityform;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

/**
 * Created by Dana Shalev on 26/11/2015.
 */
public class EntityFormHeader extends BaseElement {

    public EntityFormHeader(Element parent) {
        super(Locator.dataAid("alm-entity-form-header-block"), parent);
    }

    public EntityFormHeader expectValueToBe(String expectedValue) {
        getDriver().expects().elementTextToBeEqual(this, expectedValue);
        return this;
    }
}
