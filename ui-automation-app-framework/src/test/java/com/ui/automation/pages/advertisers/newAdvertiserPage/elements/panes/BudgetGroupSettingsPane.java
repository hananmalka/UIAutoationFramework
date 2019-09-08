package com.ui.automation.pages.advertisers.newAdvertiserPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.AutoCompleteTag;
import com.ui.automation.elements.controls.ToggleButton;
import com.ui.automation.elements.controls.textbox.TextBox;
import com.ui.automation.locator.Locator;

public class BudgetGroupSettingsPane extends Pane {

    public BudgetGroupSettingsPane(BaseElement parent) {
        super(Locator.className("budgetGroupSettings"), parent);
    }

    public TextBox groupNameTextBox() {
        return textBoxField(Locator.id("budget-group-name-input"));
    }

    public AutoCompleteTag campaignsTagField() {
        return autoCompleteTagField(Locator.id("budget-group-campaigns-tagsinput"));
    }

    public ToggleButton budgetAutoManager() {
        return toggleButton();
    }
}
