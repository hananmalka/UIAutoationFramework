package com.ui.automation.pages.campaigns.campaignsPage.elements;

import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.controls.dropdowns.ReactSelectDropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.elements.filter.Filter;
import com.ui.automation.locator.Locator;

public class CampaignsPageFilter extends Filter {

    public CampaignsPageFilter(Element parent) {
        super(parent);
    }

    public TextBox searchBox() {
        return searchBox(Locator.id("campaigns-list-search"));
    }

    public ReactSelectDropDown publishersFilter() {
        return dropDownFilter(Locator.id("campaigns-list-filter-publisher"));
    }

    public ReactSelectDropDown countriesFilter() {
        return dropDownFilter(Locator.id("campaigns-list-filter-country"));
    }

    public ReactSelectDropDown placementsFilter() {
        return dropDownFilter(Locator.id("campaigns-list-filter-placement"));
    }

    public ReactSelectDropDown ownersFilter() {
        return dropDownFilter(Locator.id("campaigns-list-filter-owner"));
    }

    public ReactSelectDropDown advertisersFilter() {
        return dropDownFilter(Locator.id("campaigns-list-filter-advertiser"));
    }

    public ReactSelectDropDown statusesFilter() {
        return dropDownFilter(Locator.id("campaigns-list-filter-status"));
    }
}
