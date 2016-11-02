package com.ui.automation.elements.entities.test;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.entitiescontainer.EntitiesContainer;

/**
 * Created by Dana Shalev on 30/11/2015.
 */
public class TestsContainer extends EntitiesContainer {

    public TestsContainer(Element parent) {
        super(parent);
    }

    public TestsToolbar toolbar() {
        return new TestsToolbar(this);
    }
}
