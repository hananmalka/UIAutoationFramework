package com.ui.automation.elements.entitiescontainer;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.fatlines.FatLines;
import com.ui.automation.elements.controls.grid.Grid;

/**
 * Created by Dana Shalev on 30/11/2015.
 */
public abstract class EntitiesContainer extends BaseElement {

    public EntitiesContainer(Element parent) {
        super(Locator.dataAid("entities-container"), parent);
    }

    public Grid grid() {
        return new Grid(this);
    }

    public FatLines fatLines() {
        return new FatLines(this);
    }
}
