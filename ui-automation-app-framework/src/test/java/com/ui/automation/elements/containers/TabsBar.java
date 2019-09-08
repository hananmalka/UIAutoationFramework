package com.ui.automation.elements.containers;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.Tab;
import com.ui.automation.locator.Locator;

/**
 * Created by azariam on 07/06/2016.
 */
public class TabsBar extends BaseElement {

    private static final String TAB_XPATH_TEMPLATE = ".//div[contains(@tabindex, '<tabIndex>')]";

    public TabsBar(Locator locator, Element parent) {
        super(locator, parent);
    }

    public int getNumberOfOpenTabs() {
        int tabCounter = 0;
        int maxTabCounter = 100;
        while (tabCounter < maxTabCounter){
            Tab tab = new Tab(Locator.xpath(TAB_XPATH_TEMPLATE.replace("<tabIndex>", String.valueOf(tabCounter))), this);
            if (!tab.isThisElementVisible()){
                return tabCounter;
            }
            tabCounter++;
        }
        return tabCounter;
    }

    public Integer getIndexOfTab(String tabTitle){

        int index = 0;
        Tab currentTab = new Tab(Locator.xpath(TAB_XPATH_TEMPLATE.replace("<tabIndex>", String.valueOf(index))), this);
        while (currentTab.isThisElementVisible()) {
            if (currentTab.getTabName().equals(tabTitle))
                return index;
            else
                index++;
            currentTab = new Tab(Locator.xpath(TAB_XPATH_TEMPLATE.replace("<tabIndex>", String.valueOf(index))), this);
        }
        return null;

    }

    public Tab getTab(String tabTitle){
        return getTab(getIndexOfTab(tabTitle));
    }

    public Tab getTab(Integer tabIndex){
        if (tabIndex == null)
            return null;
        return new Tab(Locator.xpath(TAB_XPATH_TEMPLATE.replace("<tabIndex>", String.valueOf(tabIndex))), this);
    }

}
