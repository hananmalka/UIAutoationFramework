package com.ui.automation.elements.scm;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.locator.Locator;

/**
 * Created by mendelan on 7/22/2016.
 */

public class ChangeListItem extends BaseElement{

    public static final Locator CHANGES_LIST_ITEM_FILENAME_LOCATOR = Locator.xpath("./span/div/div/div[2]");
    public static final Locator CHANGES_LIST_ITEM_FOLDER_PATH_LOCATOR = Locator.xpath("./span/div/div/div[3]");

    public static final Locator CHANGES_LIST_ITEM_FLOW_ADDED_ICON = Locator.className("icon-flow-added");
    public static final Locator CHANGES_LIST_ITEM_FLOW_MODIFIED_ICON = Locator.className("icon-flow-modified");
    public static final Locator CHANGES_LIST_ITEM_FLOW_DELETED_ICON = Locator.className("icon-flow-deleted");

    public static final Locator CHANGES_LIST_ITEM_SP_ADDED_ICON = Locator.className("icon-system-properties-added");
    public static final Locator CHANGES_LIST_ITEM_SP_MODIFIED_ICON = Locator.className("icon-system-properties-modified");
    public static final Locator CHANGES_LIST_ITEM_SP_DELETED_ICON = Locator.className("icon-system-properties-deleted");

    private int index;

    public ChangeListItem(Locator locator, Element parent) {
        super(locator, parent);
    }

    public ChangeListItem(Locator locator, Element parent, int index) {
        super(locator, parent);
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }

    public BaseElement nameField() {
        return new BaseElement(CHANGES_LIST_ITEM_FILENAME_LOCATOR, this);
    }

    public BaseElement folderPathField() {
        return new BaseElement(CHANGES_LIST_ITEM_FOLDER_PATH_LOCATOR, this);
    }

    public BaseElement flowAddedIcon() {
        return new BaseElement(CHANGES_LIST_ITEM_FLOW_ADDED_ICON, this);
    }

    public BaseElement flowModifiedIcon() {
        return new BaseElement(CHANGES_LIST_ITEM_FLOW_MODIFIED_ICON, this);
    }

    public BaseElement flowDeletedIcon() {
        return new BaseElement(CHANGES_LIST_ITEM_FLOW_DELETED_ICON, this);
    }

    public BaseElement spAddedIcon() {
        return new BaseElement(CHANGES_LIST_ITEM_SP_ADDED_ICON, this);
    }

    public BaseElement spModifiedIcon() {
        return new BaseElement(CHANGES_LIST_ITEM_SP_MODIFIED_ICON, this);
    }

    public BaseElement spDeletedIcon() {
        return new BaseElement(CHANGES_LIST_ITEM_SP_DELETED_ICON, this);
    }

    public void expectColorRed() {
        new BaseElement(Locator.xpath(".//div[contains(@style, 'color:#ff0000')]"), this).expectVisible();
    }

    public void expectColorBlue() {
        new BaseElement(Locator.xpath(".//div[contains(@style, 'color:#0000ff')]"), this).expectVisible();
    }

    public void expectColorGreen() {
        new BaseElement(Locator.xpath(".//div[contains(@style, 'color:#00ff00')]"), this).expectVisible();
    }

    public void expectColorGray() {
        new BaseElement(Locator.xpath(".//div[contains(@style, 'color:#616161')]"), this).expectVisible();
    }

}
