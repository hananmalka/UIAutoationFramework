package com.ui.automation.elements.entities.appmodule;

import com.ui.automation.elements.entitytree.EntityTree;

/**
 * Created by Dana Shalev on 24/11/2015.
 */
public class AppModuleTree extends EntityTree {

    public AppModuleTree() {
        super("Application Modules");
    }

    public AppModulesToolbar toolbar() {
        return new AppModulesToolbar(this);
    }
}
