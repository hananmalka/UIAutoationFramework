package com.ui.automation.elements.containers;

import com.ui.automation.elements.controls.Button;
import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

/**
 * Created by Dana Shalev on 30/11/2015.
 */
public class Toolbar extends BaseElement {

    String buttonPrefix;

    public Toolbar(Locator self, BaseElement parent){
        super(self, parent);
    }

    public Toolbar(String buttonPrefix, Locator locator, Element parent) {
        super(locator, parent);
        this.buttonPrefix = buttonPrefix;
    }


    public Button button(String name) {
        String dataAid =  buttonPrefix + name.toLowerCase().trim().replace(" ", "-");
        return button(Locator.xpath(".//*[contains(@data-aid, '" + dataAid + "')]"));
    }

    public Button button(Locator locator) {
        return new Button(locator, this);
    }
}
