package com.ui.automation.elements.controls.table;


import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;

public class TableHeader extends BaseElement {
    private static final String GRID_HEADER_CLASS = "react-bs-container-header";

    public TableHeader(Element parent) {
        super(Locator.className(GRID_HEADER_CLASS), parent);
    }

    public void clickColumn(String partialColumnDataAid) {
        getDriver().actions().click(getColumnElement(partialColumnDataAid));
    }

    private Element getColumnElement(String headerTitle) {
        return new BaseElement(Locator.xpath("//th[text()='" + headerTitle + "']"), this);
    }
}
