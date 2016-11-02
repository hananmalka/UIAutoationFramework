package com.ui.automation.selenium.wd.specificElements;

import com.ui.automation.elements.api.Element;

/**
 * Created with IntelliJ IDEA.
 * User: yasterzo
 * Date: 15/06/14
 * Time: 17:32
 * To change this template use File | Settings | File Templates.
 */
public interface VisibleAfterAction {

    /**
     * Method will implement click action on actionElement till expectedElement occur or timeout
     */
    boolean click(Element actionElement, Element expectedElement);



}
