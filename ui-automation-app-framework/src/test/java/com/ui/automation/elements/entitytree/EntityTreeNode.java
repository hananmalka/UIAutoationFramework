package com.ui.automation.elements.entitytree;

import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.selenium.wd.Expects;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Dana Shalev on 25/11/2015.
 */
public class EntityTreeNode extends BaseElement {

    BaseElement hasNoChildrenNode, expandedNode, collapsedNode;

    public EntityTreeNode(Locator locator, Element parent) {
        super(locator, parent);
        expandedNode = new BaseElement(Locator.xpath(".//span[contains(@class, 'dynatree-expanded')]"), this);
        collapsedNode = new BaseElement(Locator.xpath(".//span[not(contains(@class, 'dynatree-expanded')) and (contains(@class, 'dynatree-has-children'))]"), this);
        hasNoChildrenNode = new BaseElement(Locator.xpath(".//span[not(contains(@class, 'dynatree-has-children'))]"), this);
    }

    public EntityTreeNode select() {
        assureNodeVisible();
        Element element = new BaseElement(Locator.className("dynatree-title"), this);
        getDriver().actions().click(element);

        return this;
    }

    public EntityTreeNode expand() {
        return setExpandedState(true);
    }

    public EntityTreeNode collapse() {
        return setExpandedState(false);
    }

    public EntityTreeNode child(String nodeName) {
        expand();
        return new EntityTreeNode(Locator.xpath(".//li[span[descendant::text()='" + nodeName + "']]"), this);
    }

    public EntityTreeNode descendant(String[] pathNodesNames) {
        EntityTreeNode child = child(pathNodesNames[0]);
        return pathNodesNames.length == 1 ? child : child.descendant(Arrays.copyOfRange(pathNodesNames, 1, pathNodesNames.length));
    }

    private EntityTreeNode setExpandedState(boolean expand) {
        assureNodeVisible();
        Element visibleElement = getDriver().expects().elementsToBeVisible(Expects.MultiElementExpectStrategy.OR, expandedNode, collapsedNode, hasNoChildrenNode);

        // in case we should expand -> expand only if the current visible node is the collapsed node
        // in case we should collapse -> collapse only if the current visible node is the expanded node
        // if the current node has no children do nothing in both cases
        if (visibleElement.equals(expand ? collapsedNode : expandedNode)) {
            BaseElement element = new BaseElement(Locator.className("dynatree-expander"), this);
            getDriver().actions().click(element);
        }

        return this;
    }

    private EntityTreeNode assureNodeVisible() {
        // expand all parents
        EntityTreeNode parent = this;
        ArrayList<EntityTreeNode> ancestors = new ArrayList<EntityTreeNode>();

        while (true) {
            parent = (parent.getParent() instanceof EntityTreeNode) ? (EntityTreeNode)parent.getParent() : null;
            if (parent == null) {
                break;
            }

            // clickAdd at index 0 since we want the list to start with the root node since this is the order we will expand the nodes
            ancestors.add(0, parent);
        }

        for (EntityTreeNode node : ancestors) {
            node.expand();
        }

        return this;
    }
}
