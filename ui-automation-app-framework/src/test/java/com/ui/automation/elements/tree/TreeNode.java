package com.ui.automation.elements.tree;

import com.ui.automation.common.element.items.SpecialKeys;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;
import com.ui.automation.selenium.wd.Expects;

import java.util.ArrayList;

/**
 * User: noym
 * Date: 04/02/2016
 * Time: 14:29
 */

public class TreeNode extends BaseElement {

    BaseElement collapsedNode, expandedNode, leafNode;

    public TreeNode(Locator locator, Element parent, String nodeName) {
        super(locator, parent);
        collapsedNode = new BaseElement(Locator.xpath(".//ul/li[contains(@class, 'jstree-close') and a/text()='" + nodeName + "']"), parent);
        expandedNode = new BaseElement(Locator.xpath(".//ul/li[contains(@class, 'jstree-open') and a/text()='" + nodeName + "']"), parent);
        leafNode = new BaseElement(Locator.xpath(".//ul/li[contains(@class, 'jstree-leaf') and a/text()='" + nodeName + "']"), parent);
    }

    public TreeNode expand() {
        return changeExpandedState(true);
    }

    public TreeNode collapse() {
        return changeExpandedState(false);
    }

    public TreeNode select() {
        assureNodeVisible();
        BaseElement element = new BaseElement(Locator.xpath(".//a"), this);
        element.clickOn();
        return this;
    }

    @Override
    public void doubleClick(){
        BaseElement element = new BaseElement(Locator.xpath(".//a"), this);
        element.doubleClick();
    }

    @Override
    public TreeNode getChild(String nodeName) {
        expand();
        return new TreeNode(Locator.xpath("./ul/li[a/text()='" + nodeName + "']"), this, nodeName);
    }

    private TreeNode changeExpandedState(boolean expand) {

        Element visibleElement = getDriver().expects().elementsToBeVisible(Expects.MultiElementExpectStrategy.OR, expandedNode, collapsedNode);

        // in case we should expand -> expand only if the current visible node is the collapsed node
        // in case we should collapse -> collapse only if the current visible node is the expanded node
        // if the current node has no children do nothing in both cases
        if (visibleElement.equals(expand ? collapsedNode : expandedNode)) {
            new BaseElement(Locator.xpath(".//i[contains(@class, 'jstree-icon jstree-ocl')]"), this).clickOn();

            if (expand){
                expandedNode.waitForElementToAppear(1,2);
            } else {
                collapsedNode.waitForElementToAppear(1,2);
            }
        }
        return this;
    }

    private TreeNode assureNodeVisible() {
        // expand all parents
        TreeNode parent = this;
        ArrayList<TreeNode> ancestors = new ArrayList<>();

        while (true) {
            parent = (parent.getParent() instanceof TreeNode) ? (TreeNode)parent.getParent() : null;
            if (parent == null) {
                break;
            }

            // clickAdd at index 0 since we want the list to start with the root node since this is the order we will expand the nodes
            ancestors.add(0, parent);
        }

        for (TreeNode node : ancestors) {
            node.expand();
        }

        return this;
    }

    public boolean isLeaf() {
        try {
            getDriver().expects().elementToBeVisible(leafNode);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public void expectedLeafNode(boolean leaf) {
        if (leaf) {
            getDriver().expects().elementToBeVisible(leafNode);
        } else {
            getDriver().expects().elementNotVisible(this, leafNode);
        }
    }

    public void expectNodeIconClassToContain(String className){
        BaseElement element = new BaseElement(Locator.xpath(".//i[contains(@class, 'jstree-icon') and contains(@class, '" + className + "')]"), this);
        element.expectVisible();
    }

    public void expectNodeToBeCollapsed() {
       collapsedNode.expectVisible();
    }

    public void expectNodeToBeExpanded() {
       expandedNode.expectVisible();
    }

    public BaseElement getCopyableElement(){
        BaseElement copyableElement = this;
        if (!this.getTagName().equalsIgnoreCase("a")) {
            copyableElement = new BaseElement(Locator.xpath(".//a"), this);
        }
        return copyableElement;
    }

    @Override
    public TreeNode sendCtrlCToElement() {
        getDriver().actions().sendSpecialKeys(getCopyableElement(), SpecialKeys.chord(SpecialKeys.CONTROL + "c"));
        return this;
    }

    @Override
    public TreeNode sendCtrlVToElement() {
        getDriver().actions().sendSpecialKeys(getCopyableElement(), SpecialKeys.chord(SpecialKeys.CONTROL + "v"));
        return this;
    }

    public TreeNode sendKeyesToElement(String keys) {
        getDriver().actions().sendSpecialKeys(getCopyableElement(), keys);
        return this;
    }

}
