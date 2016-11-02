package com.ui.automation.elements.containers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 26/05/2016.
 * This class represents panes in html
 * There's no special behavior, this is simply a container to fit the logic of how the page is built.
 */
public class Pane extends BaseElement {

    public Pane (Locator self, BaseElement parent){
        super(self, parent);
    }

}
