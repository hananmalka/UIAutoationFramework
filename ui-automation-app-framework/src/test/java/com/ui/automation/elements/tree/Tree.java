package com.ui.automation.elements.tree;

import com.ui.automation.common.element.items.SpecialKeys;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

/**
 * User: noym
 * Date: 04/02/2016
 * Time: 11:26
 */

//this tree is based on the jstree structure
public class Tree extends BaseElement {

    private String selectedNodeClass;

    public Tree(Locator locator, Element treeContainer, String selectedNodeClass) {
        super(locator, treeContainer);
        setSelectedNodeClass(selectedNodeClass);
    }

    public TreeNode root(String rootName) {
        return new TreeNode(Locator.xpath(".//div/ul/li[a/text()='" + rootName + "']"), this, rootName);
    }

    public TreeNode getNode(String path) {
        String[] nodesNames = path.split("/");
        TreeNode node = this.root(nodesNames[0]);
        for (int i = 1; i < nodesNames.length; i++) {
            node = node.getChild(nodesNames[i]);
        }
        return node;
    }

    public void selectPath(String path) {
        getNode(path).select();
    }

    public void expandNodeByPath(String path) {
        getNode(path).expand();
    }

    public void collapseNodeByPath(String path) {
        getNode(path).collapse();
    }

    public void expectedLeafNode(String path, boolean leaf) {
        getNode(path).expectedLeafNode(leaf);
    }

    public void expectNodeToBeCollapsed(String path){
        getNode(path).expectNodeToBeCollapsed();
    }

    public void expectNodeToBeExpanded(String path){
        getNode(path).expectNodeToBeExpanded();
    }

    public void expectNodeIconToBe(String path, String expectedClass) {
        getNode(path).expectNodeIconClassToContain(expectedClass);
    }

    public void expectSelectedNodeToBe(String text) {
        BaseElement selectedNode = new BaseElement(Locator.xpath(".//a[contains(@class, '" + getSelectedNodeClass() + "')]"), this);
        selectedNode.expectVisible();
        getDriver().expects().elementTextToBeEqual(selectedNode, text);
    }

    public void expectSelectedNodeToBe(String text, String className) {
        BaseElement selectedNode = new BaseElement(Locator.xpath(".//a[contains(@class, '" + className + "')]"), this);
        selectedNode.expectVisible();
        getDriver().expects().elementTextToBeEqual(selectedNode, text);
    }

    public String getSelectedNodeClass() {
        return selectedNodeClass;
    }

    public void setSelectedNodeClass(String selectedNodeClass) {
        this.selectedNodeClass = selectedNodeClass;
    }

    public void ctrlC(String path) {
        getNode(path).select();
        getNode(path).getCopyableElement().sendCtrlCToElement();
    }

    public void ctrlV(String path) {
        getNode(path).select();
        getNode(path).sendCtrlVToElement();
    }

    public void altW(String path) {
        getNode(path).select();
        getNode(path).sendKeyesToElement(SpecialKeys.chord(SpecialKeys.ALT + "w"));
    }

    public void altS(String path) {
        getNode(path).select();
        getNode(path).sendKeyesToElement(SpecialKeys.chord(SpecialKeys.ALT + "s"));
    }

    public void altC(String path) {
        getNode(path).select();
        getNode(path).sendKeyesToElement(SpecialKeys.chord(SpecialKeys.ALT + "c"));
    }

    public void altI(String path) {
        getNode(path).select();
        getNode(path).sendKeyesToElement(SpecialKeys.chord(SpecialKeys.ALT + "i"));
    }
}
