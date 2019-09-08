package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.dropdowns.DropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.elements.entities.Campaign;
import com.ui.automation.locator.Locator;

public class MainPane extends Pane {

    public MainPane(BaseElement parent) {
        super(Locator.className("main"), parent);
    }

    public DropDown advertiserDropDown() {
        return dropDownField(Locator.id("advertiserDropdown"));
    }

    public DropDown titleDropDown() {
        return dropDownField(Locator.id("advertiserTitleDropdown"));
    }

    public TextBox bidTextBox() {
        return textBoxField(Locator.id("bid"));
    }

    public Button costModelButton(String costModel) {
        return buttonField(Locator.id(costModel));
    }

    public void fillAll(Campaign campaign) {
        advertiserDropDown().selectItem(campaign.getAdvertiserTitles().getAdvertisers().get(0).getName());
        titleDropDown().selectItem("Hopeless: The Dark Cave");
        bidTextBox().setValue("10");
        costModelButton(campaign.getCostModel()).click();
    }
}
