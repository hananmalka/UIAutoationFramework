package com.ui.automation.elements.entities.appmodule;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.entitytree.EntityTreeToolbar;

/**
 * Created by Dana Shalev on 24/11/2015.
 */
public class AppModulesToolbar extends EntityTreeToolbar {

    public AppModulesToolbar(Element parent) {
        super(parent);
    }

    public AppModulesToolbar clickEnterEdit() {
        button("enter-edit").click();
        return this;
    }

    public AppModulesToolbar clickAdd() {
        button("add").click();
        return this;
    }

    public AppModulesToolbar clickExitEdit() {
        button("exit-edit").click();
        return this;
    }
}
