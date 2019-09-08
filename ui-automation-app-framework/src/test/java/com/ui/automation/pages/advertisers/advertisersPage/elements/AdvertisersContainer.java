package com.ui.automation.pages.advertisers.advertisersPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.controls.table.Table;
import com.ui.automation.locator.Locator;

public class AdvertisersContainer extends BaseElement {

    public AdvertisersPageFilter advertiserPageFilter;
    public Table advertiserPageTable;

    public AdvertisersContainer(Element parent) {
        super(Locator.xpath("//*[contains(@class, 'advertisers-container')]//*[@class='component-content']"), parent);
        advertiserPageFilter = new AdvertisersPageFilter(this);
        advertiserPageTable = new Table(this);
    }
}
