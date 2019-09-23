package com.ui.automation.pages.campaigns.newCampaginPage.elements.panels;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Panel;
import com.ui.automation.elements.controls.Button;
import com.ui.automation.elements.controls.IControl;
import com.ui.automation.elements.controls.dropdowns.DropDown;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.elements.entities.AdvertiserTitles;
import com.ui.automation.elements.entities.Bids;
import com.ui.automation.elements.entities.Campaign;
import com.ui.automation.locator.Locator;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPanel extends Panel {

    private MainPanelFields mainPanelFields;

    public MainPanel(BaseElement parent) {
        super(Locator.className("main"), parent);
        mainPanelFields = new MainPanelFields();
    }

    public void setAdvertiser(String advertiser) {
        advertiserDropDown().selectItem(advertiser);
    }

    public void setTitle(String title) {
        titleDropDown().selectItem(title);
    }

    public void setBid(String bid) {
        bidTextBox().setValue(bid);
    }

    public void setCostModel(String costModel) {
        costModelButton(costModel).click();
    }

    private DropDown advertiserDropDown() {
        return dropDownField(Locator.id("advertiserDropdown"));
    }

    private DropDown titleDropDown() {
        return dropDownField(Locator.id("advertiserTitleDropdown"));
    }

    private TextBox bidTextBox() {
        return textBoxField(Locator.id("bid"));
    }

    private Button costModelButton(String costModel) {
        return buttonField(Locator.id(costModel));
    }

    public void fillPanel(Campaign campaign) {
        try {
            Field[] campaignObjectFields = Campaign.class.getDeclaredFields();
            for (Field field : campaignObjectFields) {
                if (mainPanelFields.fieldToElementsMap.get(field.getType()) != null) {
                    for (int i = 0; i < mainPanelFields.fieldToElementsMap.get(field.getType()).size(); i++) {
                        mainPanelFields.fieldToElementsMap.get(field.getType()).get(i).setValue(campaign.getCampaignWebFields().objectFieldToWebElementMap.get(field).get(i));
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private class MainPanelFields {

        Map<Object, List<IControl>> fieldToElementsMap = new HashMap<>();

        MainPanelFields() {
            fieldToElementsMap.put(AdvertiserTitles.class, Arrays.asList(advertiserDropDown(), titleDropDown()));
        }
    }
}
