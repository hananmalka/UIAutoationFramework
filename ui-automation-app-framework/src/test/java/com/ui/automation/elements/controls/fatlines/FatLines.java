package com.ui.automation.elements.controls.fatlines;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

/**
 * Created by Dana Shalev on 30/11/2015.
 */
public class FatLines extends BaseElement {

    public FatLines(Element parent) {
        super(Locator.dataAid("mqm-entity-fat-lines"), parent);
    }

    public Element getLineByName(String text) {
        return new FatLine(Locator.xpath(".//*[@data-aid='mqm-entity-fat-lines-entity-field-name' and descendant::*[contains(text(), '" + text + "')]]"), this);
    }
}
