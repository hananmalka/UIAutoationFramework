package com.ui.automation.elements.entitytree;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.containers.Toolbar;

/**
 * Created by Dana Shalev on 19/11/2015.
 */
public class EntityTreeToolbar extends Toolbar {

    public EntityTreeToolbar(Element parent) {
        // TODO: this locator searches from the top of the document -> can we search from the parent (try "..//")
        super("tree-toolbar-", Locator.xpath("//div[contains(@data-aid, 'tree-module-toolbar')]"), parent);
    }
}
