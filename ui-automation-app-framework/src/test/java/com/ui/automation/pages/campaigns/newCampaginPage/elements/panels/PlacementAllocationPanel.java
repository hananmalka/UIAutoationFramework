package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.CheckBox;
import com.ui.automation.enums.CheckBoxLabels;
import com.ui.automation.locator.Locator;

import java.util.Map;

public class PlacementAllocationPanel extends Panel {

    static PublishersPanel publishersPanel;

    public PlacementAllocationPanel(BaseElement parent) {
        super(Locator.id("placement-allocation"), parent);
        publishersPanel = new PublishersPanel(this);
    }

    public CheckBox checkBoxField(CheckBoxLabels.DefaultPlacementsLabels checkBoxLabel) {
        return checkBoxField(Locator.xpath("//label[text()='" + checkBoxLabel.getDisplayableLabel() + "']/preceding-sibling::label"));
    }

    public void setCheckBoxes(Map<CheckBoxLabels.DefaultPlacementsLabels, Boolean> checkBoxesAndValues) {
        checkBoxesAndValues.forEach((checkBoxLabel, value) ->
                checkBoxField(Locator.xpath("//label[text()='" + checkBoxLabel.getDisplayableLabel() + "']/preceding-sibling::label")).set(value));
    }
}
