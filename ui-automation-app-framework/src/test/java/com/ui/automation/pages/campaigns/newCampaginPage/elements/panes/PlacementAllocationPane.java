package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.CheckBox;
import com.ui.automation.enums.CheckBoxLabels;
import com.ui.automation.locator.Locator;

import java.util.List;
import java.util.Map;

public class PlacementAllocationPane extends Pane {

    static PublishersPane publishersPane;

    public PlacementAllocationPane(BaseElement parent) {
        super(Locator.id("placement-allocation"), parent);
        publishersPane = new PublishersPane(this);
    }

    public CheckBox checkBoxField(CheckBoxLabels.DefaultPlacementsLabels checkBoxLabel) {
        return checkBoxField(Locator.xpath("//label[text()='" + checkBoxLabel.getDisplayableLabel() + "']/preceding-sibling::label"));
    }

    public void setCheckBoxes(Map<CheckBoxLabels.DefaultPlacementsLabels, Boolean> checkBoxesAndValues) {
        checkBoxesAndValues.forEach((checkBoxLabel, value) ->
                checkBoxField(Locator.xpath("//label[text()='" + checkBoxLabel.getDisplayableLabel() + "']/preceding-sibling::label")).set(value));
    }
}
