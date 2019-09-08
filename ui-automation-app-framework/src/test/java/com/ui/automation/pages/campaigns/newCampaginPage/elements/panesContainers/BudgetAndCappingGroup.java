package com.ui.automation.pages.campaigns.newCampaginPage.elements.panesContainers;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Form;
import com.ui.automation.elements.controls.AutoCompleteTag;
import com.ui.automation.elements.controls.dropdowns.AuraDropDown;
import com.ui.automation.enums.LimitationEnum;
import com.ui.automation.locator.Locator;
import com.ui.automation.pages.campaigns.newCampaginPage.elements.panes.LimitationRowPane;

import java.util.List;

public class BudgetAndCappingGroup extends Form {

    BudgetAndCappingGroup(BaseElement parent) {
        super(Locator.className("capping-section"), parent);
    }

    public LimitationRowPane limitationRowPane(LimitationEnum limitationEnum) {
        return new LimitationRowPane(limitationEnum, this);
    }

    public AutoCompleteTag mailingListField() {
        return autoCompleteTagField();
    }

    public AuraDropDown addLimitationDropDown() {
        return auraDropDownField();
    }

    public void setMailingList(List<String> emails) {
        emails.forEach(email -> autoCompleteTagField().addNewTag(email));
    }
}
