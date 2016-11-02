package com.ui.automation.elements.entitytree;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.base.BaseTopLevelElement;

/**
 * Created by Dana Shalev on 24/11/2015.
 */
public abstract class EntityTree extends BaseTopLevelElement {

    private String rootName;

    public EntityTree(String rootName) {
        super(Locator.dataAid("alm-entity-tree"));
        this.rootName = rootName;
    }

    public EntityTreeNode root() {
        return new EntityTreeNode(Locator.xpath(".//li[span[descendant::text()='" + rootName + "']]"), this);
    }
}
