package com.ui.automation.pages.advertisers.advertisersPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.controls.dropdowns.ReactSelectDropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.elements.filter.Filter;
import com.ui.automation.locator.Locator;

public class AdvertisersPageFilter extends Filter {

    public AdvertisersPageFilter(Element parent) {
        super(parent);
    }

    public TextBox searchBox() {
        return searchBox(Locator.id("advertisers-search"));
    }

    public ReactSelectDropDown ownerFilter() {
        return dropDownFilter(Locator.xpath("//*[contains(@name,'owner-filter')]//parent::*[contains(@class,'filter')]"));
    }

    public ReactSelectDropDown statusFilter() {
        return dropDownFilter(Locator.xpath("//*[contains(@name,'statues-filter')]//parent::*[contains(@class,'filter')]"));
    }
}
