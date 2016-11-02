package com.ui.automation.elements.controls.grid;


import com.ui.automation.locator.Locator;
import com.ui.automation.elements.api.Element;
import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.selenium.wd.ExpectedResult;
import com.ui.automation.selenium.wd.RepeatableAction;

public class GridHeader extends BaseElement {
    private static final String GRID_HEADER_CLASS = "slick-header";

    public GridHeader(Element parent) {
        super(Locator.className(GRID_HEADER_CLASS), parent);
    }

    public void clickColumn(String partialColumnDataAid) {
        getDriver().actions().click(getColumnElement(partialColumnDataAid));
    }

    public void setSelectAllCheckbox(boolean check) {
        /*Element baseCb = new BaseElement(Locator.xpath(".//input[@type='checkbox']"),getColumnElement("isSelected"));
        Element baseCbWrapper = new BaseElement(Locator.xpath(".//label"),getColumnElement("isSelected"));
         getDriver().actions().setCheckbox(baseCbWrapper,baseCb,check);
*/
        getDriver().expects().repeatUntil(clickAllCheckBox(),inputCheckOrNot(check));
    }




    private Element getColumnElement(String partialColumnDataAid) {
        return new BaseElement(Locator.dataAid("grid_header_" + partialColumnDataAid), this);
    }


    private RepeatableAction clickAllCheckBox() {
        Element baseCbWrapper = new BaseElement(Locator.xpath(".//label"),getColumnElement("isSelected"));
        return new RepeatableAction(RepeatableAction.ActionType.CLICK, baseCbWrapper);
    }

    private ExpectedResult inputCheckOrNot(boolean isChecked) {
        Element baseCbInput = new BaseElement(Locator.xpath(".//input[@type='checkbox']"),getColumnElement("isSelected"));
        if(isChecked) {
            return new ExpectedResult(ExpectedResult.ResultType.SELECTED, baseCbInput);
        }
        else {
             return new ExpectedResult(ExpectedResult.ResultType.NOT_SELECTED, baseCbInput);
        }
    }

}
