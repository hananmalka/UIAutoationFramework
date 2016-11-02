package com.ui.automation.elements.entitiescontainer;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.containers.Toolbar;

/**
 * Created by Dana Shalev on 19/11/2015.
 */
public class EntitiesContainerToolbar extends Toolbar {

    public EntitiesContainerToolbar(Element parent) {
        super("entities-container-toolbar-", Locator.dataAid("toolbar"), parent);
    }
}
