package com.ui.automation.pages.campaigns.newCampaginPage.elements.panes;

import com.ui.automation.elements.base.BaseElement;
import com.ui.automation.elements.containers.Pane;
import com.ui.automation.elements.controls.CheckBox;
import com.ui.automation.elements.controls.textbox.MultiLineTextBox;
import com.ui.automation.enums.TextBoxLabels;
import com.ui.automation.locator.Locator;

public class EngageNotificationSettingsPane extends Pane {

    public EngageNotificationSettingsPane(BaseElement parent) {
        super(Locator.className("campaignNotifications"), parent);
    }

    public CheckBox engageNotificationCheckbox() {
        return checkBoxField();
    }

    public MultiLineTextBox mainTextBox() {
        String mainTextFieldLabel = TextBoxLabels.EngageNotificationsSettingsLabels.MAIN_TEXT.getDisplayableLabel();
        return multiLineTextBox(mainTextFieldLabel);
    }

    public MultiLineTextBox bodyTextBox() {
        String bodyTextFieldLabel = TextBoxLabels.EngageNotificationsSettingsLabels.BODY_TEXT.getDisplayableLabel();
        return multiLineTextBox(bodyTextFieldLabel);
    }
}
