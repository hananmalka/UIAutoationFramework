package com.ui.automation.elements.entities.test;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.entitiescontainer.EntitiesContainerToolbar;

/**
 * Created by Dana Shalev on 30/11/2015.
 */
public class TestsToolbar extends EntitiesContainerToolbar {

    public TestsToolbar(Element parent) {
        super(parent);
    }

    public TestsToolbar clickGridView() {
        button(Locator.dataAid("grid-view toolbar-toggle-button")).click();
        return this;
    }

    public TestsToolbar clickFatLinesView() {
        button(Locator.dataAid("smart-list-view toolbar-toggle-button")).click();
        return this;
    }

    public TestsToolbar clickAdd() {
        button("add").click();
        return this;
    }

    public TestsToolbar clickDelete() {
        button("delete").click();
        return this;
    }

    public TestsToolbar clickIncludeChildren() {
        button("include-children").click();
        return this;
    }
}
