package com.ui.automation.selenium.wd.specificElements;

import com.ui.automation.elements.api.Element;

/**
 * Created with IntelliJ IDEA.
 * User: coheney
 * Date: 04/05/14
 * Time: 13:32
 * To change this template use File | Settings | File Templates.
 */
public interface HeaderSubMenu {
    /**
     * The method select submenu item by given element
     * SubMenu is the menu in the element header<br>
     * The method will try to find and click on the element with validations: <br>
     * 1) If it is long list and have arrows for navigation it will navigate with the arrows
     * 2) If if totally collapsed it will open the drop down and select your item.
     * @param elementToSelect
     */
    void selectSubMenuItem(Element subMenuParent,String subItemSuffix);
}
